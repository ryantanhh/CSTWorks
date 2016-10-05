Some tests for assignment 3
---------------------------

The input file 2600-3.txt is a slightly-modified version of 2600.txt from

  http://www.gutenberg.org/files/2600/2600.zip

which is a non-copyrighted copy of "War and Peace" by Leo Tolstoy. 

The output files were generated (in Linux) as follows:

./csort 2600-3.txt > asc
./csort -r 2600-3.txt > rev
./csort -c 2600-3.txt > case
./csort -c -r 2600-3.txt > case-rev
./csort -w 2600-3.txt > ws
./csort -w -r 2600-3.txt > ws-rev
./csort -c -w 2600-3.txt > case-ws
./csort -r -c -w 2600-3.txt > case-ws-rev
./csort < 2600-3.txt > asc-stdin
./csort 2600-3.txt 2600-3.txt > asc2
./csort -c 2600-3.txt 2600-3.txt > case2

where csort is the name of the program.

If we run the debug version (called csort-debug) thus:

./csort-debug 2600-3.txt > OUT 2> DEBUG

then the DEBUG file should contain 17 stars since 2600-3.txt has 65008
lines and 2 ^ 15 = 32768 & 2 ^ 16 = 65536.

The runtests script may be used to automatically run the tests under Linux
& cygwin.  It assumes that your program name is csort.exe (under cygwin
bash shell) or csort (under Linux bash shell).  However, the script does
not test debug information.

aw
