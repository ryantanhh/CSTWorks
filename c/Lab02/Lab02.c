#include <stdio.h>
#include <ctype.h>
#include <string.h>

#define BRIGHT_RED "\33[1;31m"
#define BRIGHT_GREEN "\33[1;32m"
#define NORMAL "\33[0m"
#define CHECK(EXPR) printf("%s ... %s\n", (EXPR) ? BRIGHT_GREEN "PASSED" NORMAL : BRIGHT_RED "FAILED" NORMAL, #EXPR)

int str_all_digit(char s[]);
int str_has_digit(char s[]);
int str_replace_all(char s[], int oldc, int newc);
size_t str_replace_first(char s[], int oldc, int newc);
size_t str_replace_last(char s[], int oldc, int newc);
int str_print(char s[]);

int main(void){
    char s1[] = "hello";
    char s2[] = "comp2511";
    char s3[] = "13316097313";
    char s4[] = "hello";
    char s5[] = "comp2511";
    char s6[] = "13316097313";
    char s7[] = "hello";
    char s8[] = "comp2511";
    char s9[] = "13316097313";

    printf("s1 = ");
    str_print(s1);
    printf("\n");
    CHECK(str_all_digit(s1) == 0);
    CHECK(str_all_digit(s2) == 0);
    CHECK(str_all_digit(s3) == 1);

    CHECK(str_has_digit(s1) == 0);
    CHECK(str_has_digit(s2) == 1);
    CHECK(str_has_digit(s3) == 1);

    CHECK(str_replace_all(s1, 'l', 't') == 2);
    CHECK(strcmp(s1, "hetto") == 0);
    CHECK(str_replace_all(s2, '1', 'x') == 2);
    CHECK(strcmp(s2, "comp25xx") == 0);
    CHECK(str_replace_all(s3, '3', '*') == 4);
    CHECK(strcmp(s3, "1**16097*1*") == 0);

    CHECK(str_replace_first(s4, 'l', 't') == (size_t) 2);
    CHECK(strcmp(s4, "hetlo") == 0);
    CHECK(str_replace_first(s5, '1', 'x') == (size_t) 6);
    CHECK(strcmp(s5, "comp25x1") == 0);
    CHECK(str_replace_first(s6, '3', '*') == (size_t) 1);
    CHECK(strcmp(s6, "1*316097313") == 0);
    
    CHECK(str_replace_last(s7, 'l', 't') == (size_t) 3);
    CHECK(strcmp(s7, "helto") == 0);
    CHECK(str_replace_last(s8, '1', 'x') == (size_t) 7);
    CHECK(strcmp(s8, "comp251x") == 0);
    CHECK(str_replace_last(s9, '3', '*') == (size_t) 10);
    CHECK(strcmp(s9, "1331609731*") == 0);
    return 0;
}


int str_all_digit(char s[]){
    size_t i;
    for (i = 0; s[i] != '\0'; i++){
        if (!isdigit(s[i])) return 0; 
    }
    return 1;
}

int str_has_digit(char s[]){
    size_t i;
    for (i = 0; s[i] != '\0'; i++){
        if (isdigit(s[i])) return 1; 
    }
    return 0;

}

int str_replace_all(char s[], int oldc, int newc){
    size_t i;
    int num_replaced = 0;
    for (i = 0; s[i] != '\0'; i++){
         if(s[i] == oldc){
             s[i] = newc;
             num_replaced++;
         }  
    }
    return num_replaced;
}

size_t str_replace_first(char s[], int oldc, int newc){
    size_t i;
    for (i = 0; s[i] != '\0'; i++){
         if(s[i] == oldc){
             s[i] = newc;
             return i;
         }  
    }
    return (size_t) -1;
}

size_t str_replace_last(char s[], int oldc, int newc) {
    size_t i;
    size_t lastMatch = (size_t) -1;
    for (i = 0; s[i] != '\0'; i++){
         if(s[i] == oldc) lastMatch = i;
    }
    if (lastMatch != (size_t) -1) s[lastMatch] = newc;

    return (size_t) lastMatch;
}

int str_print(char s[]){
    size_t i;
    
    for (i = 0; s[i] != '\0'; i++){
        printf("%c", s[i]);
    }
    return i;
}

