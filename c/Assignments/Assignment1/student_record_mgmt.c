/******************************************************************************/
/* File name  : student_record_mgmt.c                                         */
/* Description: Comp2511 assignment 1. A program that let the user enter the  */
/*              student's name and score, store them to a file and retrieve   */
/*              them later.                                                   */
/* Student    : Tan, Haihua A00950721                                         */
/* Date       : 2016/06/25                                                    */
/******************************************************************************/

#include <stdio.h> 
#include <ctype.h>
#include <string.h>

#define LINESIZE 128
#define NAMESIZE 16
#define RECORDSIZE 33

#define FILE_OPEN_ERROR 1
#define FILE_CLOSE_ERROR 2
#define FILE_READ_WRITE_ERROR 3

/******************************************************************************/
/* Declaration of the functions in the program                                */
/******************************************************************************/
int menu(const char *choices[]);
int enter_records(FILE *fp); 
int display_records(FILE *fp);
int enter_name(char firstname[], char lastname[], const char *instruction); 
int enter_score(int *score);
int is_name_valid(const char name[], const char identifier[]);
int str_has_digit(const char s[]);
void format_string(char s[]); 

/* Main function: entry of the program*/
int main(void){
	const char *choices[] = {"Enter Score", "Display Score", "Quit", 0};
	int menu_idx;
	int status;
	FILE *fp;

	/* Open the file to store records, exit if fails.*/
	if ((fp = fopen("records.txt","wb+")) ==0){
		perror("fopen");
		return FILE_OPEN_ERROR;
	}

	/* get user choice from menu function, and invoke coresping modules*/
	while (1) {
		printf("\n");

		/* Quit if EOF encountered.*/
		menu_idx = menu(choices);	
		if (menu_idx == 0)
			break;

		/* Invoke coresponding module with the returned menu number*/
		switch(menu_idx){
			case 1:
				status = enter_records(fp);
				break;

			case 2:
				status = display_records(fp);
				break;

			case 3:
				goto CLEANUP;

		}

		/* All the modules retur 0 if work normally. Quit if non-zero. */
		if (status != 0)
			break;
	}

	CLEANUP:

	/* Close the opened file, return error code if failed.*/
	if (fclose(fp) != 0){
		perror("fclose");
		return FILE_CLOSE_ERROR;
	}

	/* Return error code if modules exit abnormally*/
	if (status != 0)
		return status;

	return 0;
}


/******************************************************************************/
/* Definitions of the functions in the program                                */
/******************************************************************************/

/* menu function shows the main menu and control the workflow. */
int menu(const char *choices[]){
	int i;
	int status;
	int menu_idx;
	char line[LINESIZE];
	const char *prompt = "Enter menu number>"; 

	while (1){
		for(i = 0; choices[i] != 0; i++)
			printf("%d. %s\n", i + 1, choices[i]);
		printf("%s", prompt);

		if (!fgets(line, LINESIZE, stdin))
			return 0;

		if (ferror(stdin))
			clearerr(stdin);

		status = sscanf(line, "%d", &menu_idx);
		if (status == 1 && menu_idx >0 && menu_idx <= i)
			return menu_idx;
	}
}

/* enter_records function accepts stduent record and save the data to a file. */
int enter_records(FILE *fp){
	char firstname[NAMESIZE];
	char lastname[NAMESIZE]; 
	int score; 

	const char* name_instruction = "\nName:\n"
		"1. Format: \"Lastname,Firstname\" OR \"Firstname Lastname\"\n"
		"2. Valid charactors: a-z, A-Z and '-'.\n\n";
	const char *score_instruction = "Score:\n0~100 inclusive.\n";

	/* Print the instruction.*/
	printf("%s%s\n",name_instruction, score_instruction);

	/* Kepp accepting data until the user enter quit signal */
	while (1) {
		if (!enter_name(firstname, lastname, name_instruction))
			break;

		if (!enter_score(&score))
			break;
		
		if (fseek(fp, 0L, SEEK_END) != 0){
			perror("fseek");
			return FILE_READ_WRITE_ERROR;
		}

		if (fprintf(fp,"%-15s%-15s%3d", firstname, lastname, score) < 0){
			perror("fprintf");
			return FILE_READ_WRITE_ERROR;
		}

		if (fflush(fp) !=0){
			perror("fflush");
			return FILE_READ_WRITE_ERROR; 
		} 

		printf("Record saved.\n");
	}
	return 0;
} 

/* display_records function retrieves and displays the designated record from
 * the file. */
int display_records(FILE *fp){
	char line[LINESIZE];
	const char* prompt = "Enter record number, '0' to quit>";
	unsigned long record_num;
	unsigned long file_size;
	char firstname[NAMESIZE];
	char lastname[NAMESIZE];
	int score;

	/* Get the file size.*/
	if (fseek(fp, 0L, SEEK_END) != 0){
		perror("fseek");
		return FILE_READ_WRITE_ERROR;
	}
	file_size = ftell(fp);


	/* Keep accepting and handling record number input*/
	while(1){
		printf("\n%s",prompt);

		/* Quit if EOF encountered.*/
		if (!fgets(line, LINESIZE, stdin))
			break;

		if (sscanf(line, "%lu", &record_num) == 1){
			/* Quit if user input '0' */
			if (record_num == 0)
				break;

			/* Retrive and show record if valid record number*/
			if (record_num * RECORDSIZE <= file_size){
				if (fseek(fp, (record_num - 1) * RECORDSIZE, SEEK_SET) != 0){
					perror("fseek");	
					return FILE_READ_WRITE_ERROR;
				}

				if (!fgets(line, RECORDSIZE + 1, fp)) {
					perror("fgets");	
					return FILE_READ_WRITE_ERROR;
				}

				if (sscanf(line, "%15s%15s%3d", firstname, lastname, &score) == 3)
					fprintf(stderr, "%s, %s: %3d\n", lastname, firstname, score);
			}
		}
	}
	return 0;
}

/* enter_name function gets name input and pass valid and formated data to the 
 * enter_record function*/ 
int enter_name(char firstname[], char lastname[], const char *instruction) {
	char line[LINESIZE];
	char input_firstname[NAMESIZE];
	char input_lastname[NAMESIZE];
	const char *prompt = "Enter student name ('!' to quit)>";
	int status;

	while(1){
		printf("%s", prompt);
		/* Quit if EOF encountered*/
		if (!fgets(line, LINESIZE, stdin))
			break;

		/* Quit if user input '!' */
		if (sscanf(line, " %[!]", input_firstname) == 1)
			break;

		/* Extract firstname and last name from input line with 2 format pattern*/
		status = sscanf(line, " %[0-9a-zA-Z-] , %[0-9a-zA-Z-]", input_lastname, input_firstname);
		if (status != 2)
			status = sscanf(line, " %[0-9a-zA-Z-] %[0-9a-zA-Z-]", input_firstname, input_lastname);

		/* If the input names valid, format and copy them to the coresponding variables*/
		if (status == 2 && is_name_valid(input_firstname, "Firstname") && is_name_valid(input_lastname,"Lastname")) {
			format_string(input_firstname);
			format_string(input_lastname);
			strcpy(firstname, input_firstname);
			strcpy(lastname, input_lastname);
			return 1;
		}
		if (status != 2)
			printf("Invalid format or characters.%s\n", instruction);
	}
	return 0;
}

/* Enter_score function accepts and validates the score input.*/
int enter_score(int *score){
	char line[LINESIZE];
	const char *prompt = "Enter student score (EOF to quit)>";
	int input_score;
	int status;

	while(1) {
		printf("%s", prompt);
		if (!fgets(line, LINESIZE, stdin))	
			break;
		status = sscanf(line, "%d" , &input_score);
		if (status == 1 && input_score >=0 && input_score <=100){
			*score = input_score;
			return 1;
		}
	}
	return 0;
}

/* Helper function to validate name input*/
int is_name_valid(const char name[], const char identifier[]){
	if (str_has_digit(name)) {
		printf("Invalid input: %s can not contain digits.\n", identifier);
		return 0;
	}
	if (strlen(name)> 15) {
		printf("Invalid input: %s can not exceed 15 characters.\n", identifier);
		return 0;
	}
	return 1;
}

/* Helper function to check whether the name string contains digits */
int str_has_digit(const char s[]){
	size_t i;
	for(i = 0; s[i] != '\0'; i++){
		if (isdigit(s[i]))
			return 1;
	}
	return 0;
}

/* Helper function to format the name strings as required */
void format_string(char s[]) {
	size_t i;
	s[0]=toupper(s[0]);
	for (i = 1; s[i] != '\0'; i++)
		s[i] = tolower(s[i]);
}
