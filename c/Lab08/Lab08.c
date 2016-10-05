#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define FILE_OPEN_ERROR 1 
#define FILE_CLOSE_ERROR 2
#define INVALID_ARGUMENT 3
#define MEMORY_ALLOCATED_FAILURE 4

#define LINESIZE 256

typedef struct {
	char **data;	/* dynamic array of lines */
	size_t nused;	/* number of lines in the dynamic array */
} lines_t;

lines_t readlines(FILE *fp);
void freelines(lines_t lines); 

int main(int argc, char* argv[]) {
	FILE *fp;
	lines_t lines;
	size_t i;

	if (argc > 2) {
		fprintf(stderr,"Too many arguments.\n\n"
				"Usage: %s <filename>.\n", argv[0]);
		return INVALID_ARGUMENT;
	}
		
	if (argc == 2) {
		if( (fp = fopen(argv[1], "r")) == 0) {
			perror("fopen");
			return FILE_OPEN_ERROR;
		}

		lines = readlines(fp);

		if (fclose(fp) != 0) {
			perror("fclose");
			return FILE_CLOSE_ERROR;
		}
	}

	if (argc == 1)
		lines = readlines(stdin);
	
	if (lines.nused > 0)
		for (i = 0; i < lines.nused; i++)
			fprintf(stdout, "%s", lines.data[i]);

	/* Free the memory*/
	if (lines.nused > 0)
		freelines(lines);
	
	return 0;
}

lines_t readlines(FILE *fp) {
	lines_t lines = {NULL,0};
	char buffer[LINESIZE];
	size_t maxnu = 0;
	char **temp;
	while (fgets(buffer, LINESIZE, fp)){
		if (lines.nused == maxnu) {
			if(maxnu == 0){	
				maxnu = 1;
#ifdef DEBUG
				fprintf(stderr, "*");
#endif
			}
			else { 
				maxnu *= 2;
#ifdef DEBUG
				fprintf(stderr, "*");
#endif
			}
			temp = (char**)realloc(lines.data, maxnu * sizeof(char*));
			if(temp == NULL){
				fprintf(stderr, "Memory allocated failed!");
				freelines(lines);
				exit(MEMORY_ALLOCATED_FAILURE);
			}
			lines.data = temp;
		}
		lines.data[lines.nused] = (char*)malloc((strlen(buffer) + 1) * sizeof(char));

		if (lines.data[lines.nused] == NULL){
			fprintf(stderr, "Memory allocated failed!");
			freelines(lines);
			exit(MEMORY_ALLOCATED_FAILURE);
		}

#ifdef DEBUG
		fprintf(stderr, ">%lu", strlen(buffer) + 1);
#endif
		strcpy(lines.data[lines.nused], buffer);
		++lines.nused;
	}
	return lines;
}

void freelines(lines_t lines) {
	size_t i;
	if (lines.nused == 0)
		return;
	
	for (i = 0; i < lines.nused; i++)
			free(lines.data[i]);
		free(lines.data);

	lines.nused = 0;
}
