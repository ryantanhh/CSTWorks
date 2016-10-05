Code organization of assignment3
================================

The source code of this program consists of 4 parts, 7 files:
 =============================================================
|  Parts                  | List of code                      |
|=========================+===================================|
|  1. Main program        | csort.c                           |
|-------------------------+-----------------------------------|
|  2. Sorting comparators | comparators.c, comparators.h      |
|-------------------------+-----------------------------------|
|  3. File reading &      | readlines.c, readlines.h          |
|     memory operation    |                                   |  
|-------------------------+-----------------------------------|
|  4. General macros      | macros.h                          |
 =============================================================


 Version release logs
 =============================================================
|  Version | Modification                                     |
|==========+==================================================|
|  V2      | 1. Fixed issue in V1: The parameters of comparing|
|          |    should be const and no __compar_fn_t cast is  |
|          |    needed when invoked                           |
|          | 2. Unified all the pointer variables to the same |
|          |    notation convention                           |
|==========+==================================================|
|  V1      | First submision                                  |
 =============================================================
