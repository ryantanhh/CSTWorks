#include<stdio.h>
#define LINESIZE 100 

int get_last_first(FILE *fp);
int get_first_last(FILE *fp);

int main(void){
    int i, n;
    double d;
    char s1[LINESIZE], s2[LINESIZE];
    /* Question a */
    n = sscanf("hello", "%4s", s1);
    /* Extract 4 characters from "hello", so s1="hell", n=1. */
    printf("=========Question a==========\n");
    printf("s1: %s\n", s1);
    printf("n: %d\n", n);

    /* Question b */
    n = sscanf("12345 32 12abc", "%2d %lf %*d %s", &i, &d, s1);
    /* Extract 2 digits from the string to i, so i=12. */
    /* Extract digits from the rest of the string until ' ' to d, so d=345.0 */
    /* Match ' 'ignore digits' ' */
    /* Extract the rest of the string to s1, so s1="12abc" */
    /* Totally 3 assignments finished, so n=3 */
    printf("=========Question b=========\n");
    printf("i: %d\n", i);
    printf("d: %f\n", d);
    printf("s1: %s\n", s1);
    printf("n: %d\n", n);

    /* Question c */ 
    n = sscanf("12 / 345", "%d/%lf", &i, &d);
    /* Extract number 12 to i */
    /* The source string contains ' ', the format string does not match, failed */
    /* Totally 1 assignment finished, so n=1*/
    /* Variable d doesn't changed, remain 345.0*/
    printf("=========Question c=========\n");
    printf("i: %d\n", i);
    printf("d: %f\n", d);
    printf("n: %d\n", n);
    
    /* Question d */ 
    n = sscanf("12/345", "%d/%lf", &i, &d);
    /* Extract number 12 to i */
    /* The source string contains '/', the format string matches*/
    /* Totally 1 assignment finished, so n=2*/
    printf("=========Question d=========\n");
    printf("i: %d\n", i);
    printf("d: %f\n", d);
    printf("n: %d\n", n);
    
    /* Question d1 */ 
    n = sscanf("12/345", "%d / %lf", &i, &d);
    /* Extract number 12 to i */
    /* The source string contains '/', the format string matches*/
    /* Totally 1 assignment finished, so n=2*/
    printf("=========Question d1========\n");
    printf("i: %d\n", i);
    printf("d: %f\n", d);
    printf("n: %d\n", n);
    /* Question e */
    n = sscanf("dos2unix", "%[a-z] %s", s1, s2);
    /* Extract lower case alphabetical characters to s1 until '2', so s1="dos" */
    /* Format string contains ' ', the source string contain 0 ' ' matches */
    /* Extract the rest characters to s2, so s2="2unix" */
    printf("=========Question e=========\n");
    printf("s1: %s\n", s1);
    printf("s2: %s\n", s2);
    printf("n: %d\n", n);

    printf("=========get_last_first=========\n");
    get_last_first(stdout);
    
    printf("=========get_first_last=========\n");
    get_first_last(stdout);
    return 0;
}

int get_last_first(FILE *fp){
    int n;
    char line[LINESIZE];
    char first_name[LINESIZE];
    char last_name[LINESIZE];
    printf("Please enter [Last name, First name]:\n");
    if (fgets(line, LINESIZE, stdin)){
	n = sscanf(line, " %[a-zA-Z-] , %[a-zA-Z-]", last_name, first_name);
	if (n == 2) {
	    fprintf(fp, "%s %s\n",first_name, last_name);
	    return 1;
	} else return -1;
    }else return 0;
    }

int get_first_last(FILE *fp){
    int n;
    char line[LINESIZE];
    char first_name[LINESIZE];
    char last_name[LINESIZE];
    printf("Please enter [First name <space> Last name]:\n");
    
    if (fgets(line, LINESIZE, stdin)){
	n = sscanf(line, " %[a-zA-Z-] %[a-zA-Z-]", first_name, last_name);
	if (n == 2) {
	    fprintf(fp, "%s %s\n",first_name, last_name);
	    return 1;
	} else return -1;
    }else return 0;
}
