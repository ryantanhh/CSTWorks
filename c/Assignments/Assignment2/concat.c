/******************************************************************************/
/* File name  : concat.c                                                      */
/* Description: Comp2511 assignment 2. A program that concates and displays   */
/*              files in different format with command line switches, similar */
/*              to the cat program in most Unix like systems.                 */
/* Student    : Tan, Haihua A00950721                                         */
/* Date       : 2016/07/06                                                    */
/******************************************************************************/

/******************************************************************************/
/* Preprocesors: header files                                                 */
/******************************************************************************/
#include <stdio.h>

/******************************************************************************/
/* Preprocesors: customed error code                                          */
/******************************************************************************/
#define FILE_OPEN_ERROR 1 
#define FILE_CLOSE_ERROR 2
#define INVALID_OPTION_ERROR 3

/******************************************************************************/
/* Preprocesors: option switches mask code                                    */
/******************************************************************************/
#define NUMBER_NONEMPTY_MASK 1u
#define DISPLAY_END_MASK 2u
#define NUMBER_ALL_LINE_MASK 4u
#define SUPPRESS_REPEAT_EMPTY_MASK 8u
#define DISPLAY_TAB_MASK 16u

/******************************************************************************/
/* Preprocesors: frequently used constants                                    */
/******************************************************************************/
#define HELP_SIZE 1024

/******************************************************************************/
/* Declaration of functions used in the program                               */
/******************************************************************************/
void display(FILE* fp, const unsigned options);

/******************************************************************************/
/* main function, entry of the program                                        */
/******************************************************************************/
int main(int argc, char* argv[]){
	int i;
	FILE* fp;
	char help[HELP_SIZE];

	/* An unsigned integer to store command line switches in bits:   
	 *    Bit 0: b, numbered non-empty lines                        
	 *    Bit 1: e, display $ at end of each line                   
	 *    Bit 2: n, number all output lines                         
	 *    Bit 3: s, suppress repeated empty output lines            
	 *    Bit 4: t, display TAB characters as ^I                    
	 *    Other bits: unused */
	unsigned options = 0u;
	
	/* Construct a help message with the constant contents and a variable     
	 * program name from the command line argument */
	sprintf(help,
			"Usage: %s [OPTION]... [FILE]...\n"
		    "Concatenate FILE(s), or standard input, to standard output.\n\n"
			"  -b  number non-empty output lines\n"
			"  -e  display $ at end of each line\n"
			"  -n  number all output lines\n"
			"  -s  suppress repeated empty output lines\n"
			"  -t  display TAB characters as ^I\n"
			"  -h  display this help and exit\n"
			"  --  mark end of switches\n\n"
			"With no FILE, read standard input.",
		   argv[0]);
	
	/* A loop to extract options from the command line arguments array. */
	for(i = 1; i < argc; i++){
		/* Find the implicit cutoff point of options and filenames, jump out 
		   if encountered */
		if (argv[i][0] != '-')
			break;
		
		/* Find the explicit cutoff point of options and filenames,jump out  
		 * if encountered */
		if (argv[i][1] == '-'){
			i++;
			break;
		}

		/* Print the help message if the exact '-h' option is supplied */
		if (argv[i][1] == 'h' && argv[i][2] == '\0'){
			fprintf(stdout,"%s\n", help);
			return 0;
		}
			
		/* Set the coresponding bits of options to 1 if valid switches 
		 * supplied */
		if (argv[i][1] == 'b'){
			options |= NUMBER_NONEMPTY_MASK;
			continue;
		}
		
		if (argv[i][1] == 'e'){
			options |= DISPLAY_END_MASK;
			continue;
		}
		
		if (argv[i][1] == 'n'){
			options |= NUMBER_ALL_LINE_MASK;
			continue;
		}
		
		if (argv[i][1] == 's'){
			options |= SUPPRESS_REPEAT_EMPTY_MASK;
			continue;
		}

		if (argv[i][1] == 't'){
			options |= DISPLAY_TAB_MASK;
			continue;
		}

		/* Display an error message and exit the program if invalid option 
		 * supplied. */
		fprintf(stderr, 
				"%s: invalid option -- %s\n"
				"Try './concat -h' for more information.\n", 
				argv[0], 
				argv[i]+1);
		return INVALID_OPTION_ERROR;
	}

	/* If '-n' supplied, ignore '-b' */
	if (options & NUMBER_ALL_LINE_MASK)
		options = options & ~0u<<1;
	
	/* If no file name supplied, get user input */
	if (i == argc)
		display(stdin,options);
	else {
		/* Otherwise, a loop to open and display the files from the rest of   
		 * the argv array */
		for (; i<argc; i++){
			/* Open file*/
			if ((fp=fopen(argv[i], "r")) == NULL){
				perror("fopen");
				return FILE_OPEN_ERROR;
			}

			display(fp, options);

			/* Close file*/
			if (fclose(fp) != 0){
				perror("fclose");
				return FILE_CLOSE_ERROR;
			}
		}
	}
	return 0;
}


/******************************************************************************/
/* Definition of functions of the program                                     */
/******************************************************************************/

/* A function display the file with different format according to options.    */
/* input-- FILE* fp: a stream for output                                      */
/*         unsigned options: each bit stands for one formatting option.       */
/* output-- void.                                                             */
void display(FILE* fp, const unsigned options){ 
	static unsigned line_num = 1; 
	static unsigned consecutive_newline = 1;
	char c;
	/* A flag to indicate whether to print the line number */
	int printnu = (options & NUMBER_ALL_LINE_MASK);
	
	while((c=fgetc(fp)) != EOF) {
		if (c == '\n') {
			/* Count the consecutive new lines */
			consecutive_newline++;
			
			/* Ignore the redundant empty line if '-s' option is set */
			if (options & SUPPRESS_REPEAT_EMPTY_MASK 
				&& consecutive_newline >2)
				continue;
			
			/* Set the printnu flag true if '-n' option is supplied  */
			printnu = (options & NUMBER_ALL_LINE_MASK 
					   && (consecutive_newline > 1)); 
		} else {
			/* Set the printnu flag true if '-n' or '-b' option is supplied 
			 * and the previous character is a '\n' */
			printnu = (options & (NUMBER_NONEMPTY_MASK | NUMBER_ALL_LINE_MASK)) 
					  && consecutive_newline ;

			/* Reset the count of consecutive new lines */
			consecutive_newline = 0;
		}

		/* Print the line number if the printnu flag is set */
		if (printnu){ 
			fprintf(stdout, "%6u  ", (line_num)++);
			printnu = 0;
		}

		/* Replace '\t' with "^I" if '-t' option is supplied */
		if (c == '\t' && (options & DISPLAY_TAB_MASK)) {
			fprintf(stdout, "^I");
			continue;
		}

		/* Insert a '$' if at the end of the line and '-e' option is supplied */
		if (c == '\n' && options & DISPLAY_END_MASK)
			fputc('$', stdout);

		/* Print the current character */
		fputc(c, stdout);
	}
}
