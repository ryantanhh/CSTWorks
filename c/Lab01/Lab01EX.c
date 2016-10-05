/* Lab01EX.c -- Practice array operation */
#include <stdio.h>
#define BRIGHT_RED "\33[1;31m"
#define BRIGHT_GREEN "\33[1;32m"
#define NORMAL "\33[0m"
#define CHECK(EXPR) printf("%s ... %s\n", (EXPR) ? BRIGHT_GREEN "PASSED" NORMAL : BRIGHT_RED "FAILED" NORMAL, #EXPR)

int array_max(int [], size_t);
size_t array_first_max_index(int [], size_t);
size_t array_last_max_index(int [], size_t);
int array_equal(int[], int[], size_t);

int main() {
    int a[] = {1, 5, 3, 6, 4, 5, 6, 3};
    int b[] = {1, 5, 5, 3, 2, 5};
    int c[] = {3, 2, 3, 6, 6, 3, 4, 6, 6};
    
    int d[] = {1, 5, 3, 6, 4, 5, 6, 3};
    int e[] = {1, 5, 5, 3, 2, 5};
    int f[] = {3, 2, 3, 6, 6, 3, 4, 6, 6};

    CHECK(array_max(a, 8) == 6);
    CHECK(array_max(b, 6) == 5);
    CHECK(array_max(c, 9) == 6);
    
    CHECK(array_first_max_index(a, 8) == (size_t) 3);
    CHECK(array_first_max_index(b, 6) == (size_t) 1);
    CHECK(array_first_max_index(c, 9) == (size_t) 3);
    
    CHECK(array_last_max_index(a, 8) == (size_t) 6);
    CHECK(array_last_max_index(b, 6) == (size_t) 5);
    CHECK(array_last_max_index(c, 9) == (size_t) 8);

    CHECK(array_equal(a, d, 8) == 1);
    CHECK(array_equal(b, e, 6) == 1);
    CHECK(array_equal(c, f, 9) == 1);
    return 0;
}


int array_max(int a[], size_t n) {
    int max = a[0];
    size_t i;
    for (i = 0; i < n; i++) {
        if(a[i] > max) {
            max = a[i];
        }
    }
    return max;
}

size_t array_first_max_index(int a[], size_t n) {
    size_t maxIndex = 0;
    int max = a[0];
    size_t i;
    for (i = 0; i < n; i++) {
        if(a[i] > max) {
            max = a[i];
            maxIndex = i;
        }
    }
    return maxIndex;

}
size_t array_last_max_index(int a[], size_t n) {
    size_t maxIndex = 0;
    int max = a[0];
    size_t i;
    for (i = 0; i < n; i++) {
        if(a[i] >= max) {
            max = a[i];
            maxIndex = i;
        }
    }
    return maxIndex;
}
int array_equal(int a[], int b[], size_t n) {
    size_t i;
    for(i = 0; i < n; i++) {
        if(a[i] != b[i]) break;  
    }
    return i == n;
}
