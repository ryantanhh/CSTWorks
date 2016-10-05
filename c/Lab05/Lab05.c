#include <stdio.h>
#define LINESIZE 32 

int menu(const char *choice[], const char *prompt);
int count_items(const char *choice[]);

int main(void){
	const char *choice[] = {"Enter data","Display data","Quit",0};
	const char *prompt="> ";
	int choice_num;
	choice_num = menu(choice, prompt);
	if (choice_num >0 && choice_num <= count_items(choice))
		printf("You choose %s\n", choice[choice_num-1]);
	return 0;
}

int menu(const char *choice[], const char *prompt){	
	char line[LINESIZE];
	int choice_num;
	int selected;
	int i;

	choice_num = count_items(choice);	
	while(1){

		for (i=0; i<choice_num; i++)
			printf("%d. %s\n", i+1, choice[i]);
		printf("%s", prompt);
		selected = 0;
		if (fgets(line, LINESIZE,stdin) == 0)

			return 0;
		sscanf(line, " %d", &selected);	
		if (selected>0 && selected <= choice_num)
			return selected;
		else
			printf("Invalid input.\n");
	}
}

int count_items(const char *choice[]){
	size_t i;
	int num;
	num = 0;
	for(i=0; choice[i] !=0; i++)
		num++;
	return num;
}
