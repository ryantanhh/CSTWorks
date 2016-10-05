#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define NLINES 256
#define LINESIZE 128
#define READINSIZE 1024

int cmp(const void *p, const void *q);
int cmp1(const void *p, const void *q);

int main(void) {
	int i;
	int n= 0;
	char templine[READINSIZE];
	char lines[NLINES][LINESIZE];
	char *plines[NLINES]; 
	
	for (n = 0; n < NLINES; n++){
		size_t l;
		if (!fgets(templine,READINSIZE,stdin))
			break;
		l = (strlen(templine) + 1 < LINESIZE)? strlen(templine) + 1 : LINESIZE;
		strncpy(lines[n], templine, l-1);
		lines[n][l] = '\0';
	}

	for (i = 0; i < n; i++)
		plines[i] = lines[i];


	qsort(plines, n, sizeof(char*) ,cmp);
	
	fprintf(stdout, "%s\n", "The sorted on pointer lines are: ");
	for(i = 0; i < n; i++)
		fprintf(stdout, "%s", plines[i]);

	fprintf(stdout, "%s\n", "The original lines are: ");
	for(i = 0; i < n; i++)
		fprintf(stdout, "%s", lines[i]);
	qsort(lines, n, LINESIZE, cmp1);

	fprintf(stdout, "%s\n", "The sorted on original lines are: ");
	for(i = 0; i < n; i++)
		fprintf(stdout, "%s", lines[i]);
	

	return 0;
}

int cmp(const void *p, const void *q){
	/*
	char **pp = (char**)p;
	char **qq = (char**)q;
	*/

	char* const *pp =(char* const *)p;
	char* const *qq =(char* const *)q;

#ifdef DEBUG
	fprintf(stderr, "DEBGU *pp: %s\n", *pp);
	fprintf(stderr, "DEBUG *qq: %s\n", *qq);
#endif
	return strcmp(*pp,*qq);

}

int cmp1(const void *p, const void *q){
	/*
	char*pp = (char*)p;
	char*qq = (char*)q;
	*/
	const char *pp = (const char *)p;
	const char *qq = (const char *)q;
		
	return strcmp(pp,qq);
}
