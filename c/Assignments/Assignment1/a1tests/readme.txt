How to use sample input/output files
====================================

Assuming that your program is called a1, run it in a cygwin shell using

  ./a1 < input 2> output.my

Then compare output.my & output.  This can be done by running

  cmp output.my output

The 2 files should be identical, i.e., cmp should not say that the 2
files are different.  (Note: Provided files are in Unix format.)

You may also be able to use the runtest script to run the test.

Put your executable (named a1.exe in cygwin) in the same directory as the
input, output & runtest files.  Then type

./runtest

aw
