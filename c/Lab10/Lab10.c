#include <stdio.h>
#include <string.h>

#define BRIGHT_RED "\33[1;31m"
#define BRIGHT_GREEN "\33[1;32m"
#define NORMAL_COLOR "\33[0m"
#define CHECK(EXPR) printf("%s ... %s\n", (EXPR) ? BRIGHT_GREEN "PASSED" NORMAL_COLOR : BRIGHT_RED "FAILED" NORMAL_COLOR, #EXPR)



int *arr_first_max(const int*a, size_t n);
int *arr_last_max(const int*a, size_t n); 
size_t str_replace_all(char *s, int oldc, int newc); 
char *str_find_last(const char *s, int c);

int main(void){
	int a[] = {1, 3, 4, 5, 3, 4, 5, 4, 5, 3};	
	char s[] = "comp2511";
	char *ss;
	size_t n;
	int *p;

	p = arr_first_max(a, 10);
	printf("arr_first_max:\n");
	CHECK(p == &a[3]);
	
	p = arr_last_max(a, 10);
	printf("arr_last_max:\n");
	CHECK(p == &a[8]);

	printf("str_replace_all:\n");
	n = str_replace_all(s, '1', '0');
	CHECK(n == 2);
	CHECK(strcmp(s, "comp2500") == 0);
	
	printf("str_find_last:\n");
	ss = str_find_last(s, 'p');
	CHECK(ss == &s[3]);


	return 0;
}

int *arr_first_max(const int*a, size_t n){
	int *max = (int*)a;
	const int *p;
	for (p=a; p<a+n; p++)
		if (*p > *max)
			max = (int*)p;
	return max;
}

int *arr_last_max(const int*a, size_t n){
	int *max = (int*)a;
	const int *p;
	for (p=a; p<a+n; p++)
		if (*p >= *max)
			max = (int*)p;
	return max;
}



size_t str_replace_all(char *s, int oldc, int newc){
	char* p;
	size_t n = 0;
	for (p=s; *p!='\0'; p++)
		if (*p == oldc) {
			*p = newc;
			n++;
		}
	return n;
}

char *str_find_last(const char *s, int c){
	const char *p;
	char* q = NULL;
	for (p = s; *p != '\0'; p++)
		if (*p == c)
			q = (char*)p;
	return q;
}
