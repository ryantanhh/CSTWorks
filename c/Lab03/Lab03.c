#include <stdio.h>

int main(void){
	int c;
	int consecutive_space = 0;
	int consecutive_new_line = 1;
	while((c=getchar()) != EOF) {
		if (c == '\t') c = ' ';

		if (c == ' ') consecutive_space++;
		else if (c == '\n') consecutive_new_line++;
		else {
			consecutive_space = 0;
			consecutive_new_line = 0;
		}
		if (consecutive_space < 2 && consecutive_new_line < 3)
	 		putchar(c);  	
	}
	return 0;
}
