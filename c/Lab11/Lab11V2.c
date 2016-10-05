#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#define IDSIZE 10
#define LINESIZE 128 
typedef struct node node;
typedef struct record record;
typedef node* list;

struct record {
	char id[IDSIZE];
	double score;
};

struct node {
	record data;
	node* next;
};

void list_init(list* plist);
int list_insert(list* plist, record* pr);
void list_print(list list);
void list_clear(list* plist);

int input_record(record* precord);

static int is_valid_id(char* id);
static int is_valid_score(double score);

int main(void){
	list student_list;
	record r;

	list_init(&student_list);

	while (input_record(&r))
		if (!list_insert(&student_list, &r))
			break;

	list_print(student_list);
	list_clear(&student_list);
	return 0;
}

void list_init(list* plist){
	*plist = NULL;
}

int list_insert(list* plist, record* pr){
	list newnode;
	list* tracer;

	newnode = (list)malloc(sizeof(node));

	strcpy(newnode->data.id, pr->id);
	newnode->data.score = pr->score;

	for (tracer =plist; *tracer!=NULL; tracer = &(*tracer)->next)
		if (strcmp(pr->id, (*tracer)->data.id) < 0)
			break;
	newnode->next = *tracer;
	*tracer = newnode;
	return 1;
}

void list_print(const list thelist){
	const node* p;
	for (p=thelist; p != NULL; p=p->next)
		fprintf(stdout, "%s, %4.1f\n", p->data.id, p->data.score);
}

void list_clear(list* plist){
	node*p, *q;
	for (p = *plist; p != NULL; p = q) {
		q = p->next;
		free(p);
	}
	*plist = NULL;
}

int input_record(record* precord){
	char line[LINESIZE];
	char id[IDSIZE];
	double score;
	int validinput = 0;

	while (!validinput){
		fprintf(stdout, "Enter student ID>");
		if (!fgets(line, LINESIZE, stdin))
			return 0;
		if (line[0] == '\n')
			return 0;
		strncpy(id, line, IDSIZE-1);
		id[IDSIZE-1] = '\0';
		validinput = is_valid_id(id);
		if (!validinput)
			fprintf(stdout, "Invalid ID, try again.\n");
	}

	validinput = 0;

	while (!validinput){
		fprintf(stdout, "Enter score>");
		if (!fgets(line, LINESIZE, stdin))
			return 0;
		if (line[0] == '\n')
			return 0;
		if (sscanf(line, "%lf", &score) != 1)
			return 0;

		validinput = is_valid_score(score);
		if (!validinput)
			fprintf(stdout, "Invalid score, try again.\n");
	}
	strcpy(precord->id, id);
	precord->score = score;
	return 1;
}

static int is_valid_id(char* id){
	size_t i;
	
	if (strlen(id) != IDSIZE - 1)
		return 0;
	if (tolower(id[0]) != 'a')
		return 0;
	for (i = 1; i < strlen(id); i++)
		if (!isdigit(id[i]))
			return 0;
	return 1;
}
static int is_valid_score(double score){
	return score >= 0 && score <= 100;
}
