test: dlist.o test.o
	gcc -o test dlist.o test.o
test.o:test.c
	gcc -c -g test.c
dlist.o: dlist.c dlist.h
	gcc -c -g dlist.c 
clean:
	rm test *.o
