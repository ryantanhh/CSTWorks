#include <stdio.h> 
#define BRIGHT_RED "\33[1;31m"
#define BRIGHT_GREEN "\33[1;32m"
#define NORMAL_COLOR "\33[0m" 
#define CHECK(EXPR) printf("%s ... %s\n", (EXPR)? BRIGHT_GREEN "PASSED" NORMAL_COLOR : BRIGHT_RED "FAILED" NORMAL_COLOR, #EXPR)

int is_permutation(unsigned long n1, unsigned long n2);
int is_staggering(unsigned long n);
int is_increasing(unsigned long n); 
int is_decreasing(unsigned long n);

int main(void) {
    unsigned long i;
    unsigned long count_staggering = 0;

    CHECK(is_permutation(123456ul, 635421ul) == 1);
    CHECK(is_permutation(12340ul, 1234ul) == 0);
    CHECK(is_permutation(777456ul, 747576ul) == 1);
    CHECK(is_staggering(6666666ul) == 0);
    CHECK(is_staggering(10000000ul) == 0);
    CHECK(is_staggering(125578ul) == 0);
    CHECK(is_staggering(755320ul) == 0);
    CHECK(is_staggering(144358ul) == 1);
    CHECK(is_staggering(11221122ul) == 1);
    CHECK(is_staggering(111222111ul) == 1);
    
    for (i=0ul; i <= 1000000000ul;i++)
	if (is_staggering(i))	
	    count_staggering ++;
    printf("There are %lu staggering number from 0 to 1000000000\n", count_staggering);
    
    return 0;
}

int is_permutation(unsigned long n1, unsigned long n2){
    int count[10] = {0};
    int r;
    size_t i;
    
    if (n1 / n2 >=10 || n2 / n1 >= 10)
	return 0;
    
    for (; n1 >0; n1 /= 10){
	r = n1 % 10;
	count[r]++;
    }

    for (; n2 >0; n2 /= 10){
	r = n2 % 10;
	count[r]--;
    }

    for (i = 0; i < 10; i++)
	if (count[i] != 0)
	    return 0;
	
    return 1;
}


int is_staggering(unsigned long n){
    short diff = 0;

    if (n < 100)
	return 0;
    
    for(; n >= 10; n /= 10) {
	if (diff * ((short) (n % 10) - (short)((n / 10) % 10)) < 0)
	    return 1;
	if ((short) (n % 10) - (short)((n / 10) % 10) != 0 )
		diff = (short) (n % 10) - (short)((n / 10) % 10); 
    }

    return 0;
}

