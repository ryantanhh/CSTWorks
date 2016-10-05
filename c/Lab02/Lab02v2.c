#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define BRIGHT_RED "\33[1;31m"
#define BRIGHT_GREEN "\33[1;32m"
#define NORMAL_COLOR "\33[0m"
#define CHECK(EXPR) printf("%s ... %s\n", (EXPR) ? BRIGHT_GREEN "PASSED" NORMAL_COLOR : BRIGHT_RED "FAILED" NORMAL_COLOR, #EXPR)

int str_all_digits(const char s[]);
int str_has_digit(const char s[]);
int str_replace_all(char s[], int oldc, int newc);
size_t str_replace_first(char s[], int oldc, int newc);
size_t str_replace_last(char s[], int oldc, int newc);

int main(void){
	char a[] = "hello";
	char b[] = "123123456";
	char c[] = "comp2511";
	char d[] = "hello";
	char e[] = "123123456";
	char f[] = "comp2511";
	char g[] = "hello";
	char h[] = "123123456";
	char i[] = "comp2511";
	
	CHECK(str_all_digits(a) == 0);
	CHECK(str_all_digits(b) == 1);
	CHECK(str_all_digits(c) == 0);

	CHECK(str_has_digit(a) == 0);
	CHECK(str_has_digit(b) == 1);
	CHECK(str_has_digit(c) == 1);
	
	CHECK(str_replace_all(a, 'l', 'x') == 2);
	CHECK(strcmp(a, "hexxo") == 0);
	CHECK(str_replace_all(b, '1', 'x') == 2);
	CHECK(strcmp(b, "x23x23456") == 0);
	CHECK(str_replace_all(c, '1', 'x') == 2);
	CHECK(strcmp(c, "comp25xx") == 0);

	CHECK(str_replace_first(d, 'l', 'x') == 2);
	CHECK(strcmp(d, "hexlo") == 0);
	CHECK(str_replace_first(e, '1', 'x') == 0);
	CHECK(strcmp(e, "x23123456") == 0);
	CHECK(str_replace_first(f, '1', 'x') == 6);
	CHECK(strcmp(f, "comp25x1") == 0);

	CHECK(str_replace_last(g, 'l', 'x') == 3);
	CHECK(strcmp(g, "helxo") == 0);
	CHECK(str_replace_last(h, '1', 'x') == 3);
	CHECK(strcmp(h, "123x23456") == 0);
	CHECK(str_replace_last(i, '1', 'x') == 7);
	CHECK(strcmp(i, "comp251x") == 0);
	return 0;
} 

int str_all_digits(const char s[]){
	size_t i;
	for (i = 0; s[i] !='\0'; i++)
		if (!isdigit(s[i])) 
			return 0;
	return 1;
}

int str_has_digit(const char s[]){
	size_t i;
	for (i = 0; s[i] !='\0'; i++)
		if (isdigit(s[i])) 
			return 1;
	return 0;
}

int str_replace_all(char s[], int oldc, int newc){
	int num_replaced = 0;
	size_t i;
	for (i = 0; s[i] != '\0'; i++){
		 if (s[i] == oldc) {
			s[i] = newc;
		  	num_replaced++; 
		 }	
	}
	return num_replaced;
}

size_t str_replace_first(char s[], int oldc, int newc){
	size_t i;
	for (i = 0; s[i] != '\0'; i++){
		if (s[i] == oldc) {
			s[i] = newc;
			return (size_t) i;
		}
	}
	return (size_t) -1;
}

size_t str_replace_last(char s[], int oldc, int newc){
	size_t i;
	size_t last_match = (size_t) -1;
	for (i = 0; s[i] != '\0'; i++){
		if (s[i] == oldc) {
			last_match = i;
		}
	}
	if (last_match != (size_t) -1){
		s[last_match] = newc;
	}
	return last_match;
}
