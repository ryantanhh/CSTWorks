#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define LINESIZE 128 

typedef struct {
	char id[10];
	int score;
} record;

typedef struct node node;
struct node {
	record data;
	node *next;
};

typedef node *list;

void list_init(list *lstp);
void list_destroy(list *lstp);
int list_insert(list *lstp, const record *recp);
void list_print(list lst);
int input_record(record *recp);

static int is_valid_id(char* id); 
static int is_valid_score(int score);

int main(void){
	list head;
	record r;
	list_init(&head);
	while(input_record(&r))
		if (!list_insert(&head, &r))
			break;
	list_print(head);
	list_destroy(&head);

	return 0;
}

void list_init(list *lstp){
	*lstp = NULL;
}

void list_destroy(list *lstp){
	node *p, *q;
	for (p = *lstp; p != NULL; p=q){
		q = p->next;
		free(p);
	}
}

int list_insert(list *lstp, const record *recp){
	node **tracer;
	list newnode;
	
	newnode = (list) malloc(sizeof(node));
	if (newnode == NULL){
		fprintf(stderr, "Memeory allocation failed.");	
		return 0;
	}
	
	for (tracer = lstp; *tracer != NULL; tracer = &(*tracer)->next)
		if (strcmp((*tracer)->data.id, recp->id) > 0)
			break;

	strcpy(newnode->data.id, recp->id);
	newnode->data.score = recp->score;

	newnode->next = (*tracer);
	*tracer= newnode;
	return 1;
}

void list_print(list lst){
	if (lst == NULL)
		fprintf(stdout, "\nThe list is empty.\n");
	else
		fprintf(stdout, "\nData in the list:\n");

	for (; lst !=NULL; lst=lst->next)
		fprintf(stdout, "%s, %-3d\n", lst->data.id, lst->data.score);
}

int input_record(record *recp){
	char id[10] = "";
	int score = -1;
	int isvalid = 0;

	char line[LINESIZE];
	while (!isvalid){
		fprintf(stdout, "Enter student ID >");
		if (!fgets(line, LINESIZE,stdin))
			return 0;
		if (line[0] == '\n')
			return 0;
		strncpy(id, line, 9);
		id[9] = '\0';
		isvalid = is_valid_id(id);
		if (!isvalid)
			fprintf(stdout, "Invalid studend id.\n");
	}

	isvalid = 0;
	while (!isvalid){
		fprintf(stdout, "Enter student score>");
		if (!fgets(line, LINESIZE,stdin))
			return 0;
		if (line[0] == '\n')
			return 0;
		if (sscanf(line, "%d", &score) != 1)
			return 0;
		isvalid = is_valid_score(score);
		if (!isvalid)
			fprintf(stdout, "Invalid score.\n");
	}

	strcpy(recp->id, id);
	recp->score = score;
	return 1;
}

static int is_valid_id(char* id) {
	size_t i;
	if (strlen(id) != 9)
		return 0;
	if (tolower(id[0]) != 'a')
		return 0;
	for (i = 1; i < strlen(id); i++)
		if (!isdigit(id[i]))
			return 0;
	return 1;
}

static int is_valid_score(int score) {
	return score >=0 && score <=100;
}
