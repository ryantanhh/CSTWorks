#include <stdio.h>

int menu(const char *choices[], const char *prompt);

int main(){
	const char *choices[] = {"Enter data", "Display data", "Exit", 0};
	const char *prompt = " >";
	int menu_idx;

	menu_idx = menu(choices, prompt);
	if (menu_idx != 0)
		printf("You select the menu item: %s\n", choices[menu_idx -1]);
	return 0;
}

int menu(const char *choices[], const char *prompt){
	int menu_idx;
	int n;
	int i;

	while(1){
		for (i = 0; choices[i] != 0; i++)
			printf("%d. %s\n", i + 1, choices[i]);
		printf("%s",prompt);

		n = fscanf(stdin, "%d", &menu_idx);

		if (feof(stdin))
			return 0;

		if (ferror(stdin))
			clearerr(stdin);
		if (n == 1 && menu_idx>0 && menu_idx <= i)
			return menu_idx;
	}
}


