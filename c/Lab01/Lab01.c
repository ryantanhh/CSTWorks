/* Lab01.c -- Practice array operations*/
#include <stdio.h>
#define CHECK(EXPR) printf("%s ... %s\n", (EXPR) ? "passed" : "FAILED", #EXPR)

int array_max(const int[], size_t);
size_t array_first_max_index(const int[], size_t);
size_t array_last_max_index(const int[], size_t);
int array_equal(const int[], const int[], size_t);

int main(void){
    int a[] = {1, 3, 5, 6, 5, 6, 4};
    int b[] = {1, 5, 2, 3, 5, 5, 2, 5, 2};
    int c[] = {3, 3, 5, 8, 6, 3, 8};
    int d[] = {3, 3, 5, 8, 6, 3, 8};
    int e[] = {1, 5, 2, 3, 5, 5, 2, 5, 2};

    CHECK(array_max(a, 7) == 6);
    CHECK(array_max(b, 9) == 5);
    CHECK(array_max(c, 7) == 8);

    CHECK(array_first_max_index(a, 7) == (size_t) 3);
    CHECK(array_first_max_index(b, 9) == (size_t) 1);
    CHECK(array_first_max_index(c, 7) == (size_t) 3);
    
    CHECK(array_last_max_index(a, 7) == (size_t) 5);
    CHECK(array_last_max_index(b, 9) == (size_t) 7);
    CHECK(array_last_max_index(c, 7) == (size_t) 6);
    
    CHECK(array_equal(c, d, 7) == 1);
    CHECK(array_equal(a, d, 7) != 1);
    CHECK(array_equal(b, e, 7) == 1);
    return 0;
}


int array_max(const int a[], size_t n) {
    size_t i;
    int max = a[0];
    for(i = 0; i < n; i++){
       if (a[i] > max) {
           max = a[i];
       } 
    }
    return max;
}

size_t array_first_max_index(const int a[], size_t n){
    size_t maxIndex = 0;
    size_t i;
    int max = a[0];
    for(i = 0; i < n; i++){
       if (a[i] > max) {
           max = a[i];
           maxIndex = i;
       } 
    }
    return maxIndex;
}

size_t array_last_max_index(const int a[], size_t n){
    size_t maxIndex = 0;
    size_t i;
    int max = a[0];
    for(i = 0; i < n; i++){
       if (a[i] >= max) {
           max = a[i];
           maxIndex = i;
       } 
    }
    return maxIndex;
    
}
int array_equal(const int a[], const int b[], size_t n){
    size_t i;
    for (i = 0; i < n; i++) {
        if (a[i] != b[i]) {break;}
    }
    return i == n;
}
