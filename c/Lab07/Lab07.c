#include <stdio.h> 
#include <string.h>

#define BRIGHT_GREEN "\033[1;32m"
#define BRIGHT_RED "\033[1;31m"
#define NORMAL_COLOR "\033[0m"

#define CHECK(EXPR) printf("%s...%s\n",(EXPR)?(BRIGHT_GREEN "PASSED" NORMAL_COLOR):(BRIGHT_RED "FAILED" NORMAL_COLOR), #EXPR)

/* pre-condition: n >= 1 */
void min_max(const int a[], size_t n, int *pmin, int *pmax);

/* pre-condition: n >= 1 */
void first_last(const int a[], size_t n, int x, size_t *pfirst, size_t *plast);

void str_reverse(char s[]);

/* pre-condition: n % 10 !=0 */
unsigned long num_reverse(unsigned long n);

unsigned num_digits(unsigned long n);


int main(void) {
	int n1[] = {5, 2, 6, 8, 8, 2, 8, 9};
	int n2[] = {2, 8, 2, 8, 9, 8, 6, 1, 7, 8};
	int n3[] = {8, 9, 8, 6, 2, 7, 8};

	char str1[] = "hello";
	char str2[] = "world";
	char str3[] = "comp2511";
	char str4[] = "";

	int max; 
	int min;

	size_t first;
	size_t last;

	min_max(n1, 8, &min, &max);
	CHECK(min == 2);
	CHECK(max == 9);
	min_max(n2, 10, &min, &max);
	CHECK(min == 1);
	CHECK(max == 9);
	min_max(n3, 7, &min, &max);
	CHECK(min == 2);
	CHECK(max == 9);

	first_last(n1, 8, 8, &first, &last);
	CHECK(first == 3);
	CHECK(last == 6);
	first_last(n2, 10, 8, &first, &last);
	CHECK(first == 1);
	CHECK(last == 9);
	first_last(n3, 7, 8, &first, &last);
	CHECK(first == 0);
	CHECK(last == 6);


	str_reverse(str1);
	str_reverse(str2);
	str_reverse(str3);
	str_reverse(str4);

	CHECK(strcmp(str1, "olleh") ==0);
	CHECK(strcmp(str2, "dlrow") ==0);
	CHECK(strcmp(str3, "1152pmoc") ==0);
	CHECK(strcmp(str4, "") ==0);

	CHECK(num_reverse(12345) == 54321);
	CHECK(num_reverse(455201) == 102554);
	CHECK(num_reverse(3329838) == 8389233);

	CHECK(num_digits(12345) == 5);
	CHECK(num_digits(455201) == 6);
	CHECK(num_digits(3329838) == 7);
	CHECK(num_digits(0) == 1);
	CHECK(num_digits(10) == 2);

	return 0;
}

void min_max(const int a[], size_t n, int *pmin, int *pmax){
	size_t i;
	*pmin = *pmax = a[0];
	for(i = 0; i < n; i++){
		if(a[i] < *pmin)		
			*pmin = a[i];
		if(a[i] > *pmax)
			*pmax = a[i];
	}
}

void first_last(const int a[], size_t n, int x, size_t *pfirst, size_t *plast){
	size_t i;
	*pfirst = (size_t) (-1);
	*plast = (size_t) (-1);
	for(i = 0; i < n; i++){
		if(a[i]== x){
			*plast = i;
			if (*pfirst == (size_t)(-1))
				*pfirst = i;
		}
	}
}

void str_reverse(char s[]){
	char temp;
	char *pleft;
	char *pright;
	if (strlen(s) == 0)
		return;	
	for(pleft=s, pright = s +strlen(s) - 1; pleft < pright; pleft++, pright--){
		temp = *pleft;
		*pleft = *pright;
		*pright = temp;
	}
}

unsigned long num_reverse(unsigned long n){
	unsigned long reverse = 0;
	for(; n>0; n/=10){
		reverse = reverse * 10 + n % 10;
	}
	return reverse;
}

unsigned num_digits(unsigned long n){
	unsigned int i;
	if (n==0)
		return 1;
	for(i = 0; n >0; n/=10)
		i++;
	return i;
}
