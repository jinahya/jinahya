#include <float.h>
#include <limits.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

#include "program2_12.h"

void program2_12() {

  if (0) {
    return;
  }

  printf("program2_11\n");
  printf("%20s %20s %20s\n", "MIN", "MAX", "UMAX");
  printf("CHAR_MIN: %d\tCHAR_MAX: %d\tUCHAR_MAX: %u\n", CHAR_MIN, CHAR_MAX, UCHAR_MAX);
  printf("SHRT_MIN: %d\tSHRT_MAX: %d\tUSHAR_MAX: %u\n", SHRT_MIN, SHRT_MAX, USHRT_MAX);
  printf("INT_MIN: %d\tMIN_MAX: %d\tUNIT_MAX: %u\n", INT_MIN, INT_MAX, UINT_MAX);
  printf("LONG_MIN: %ld\tLONG_MAX: %ld\tULONG_MAX: %lu\n", LONG_MIN, LONG_MAX, ULONG_MAX);
  printf("LLONG_MIN: %lld\tLLONG_MAX: %lld\tULlONG_MAX: %llu\n", LLONG_MIN, LLONG_MAX, ULLONG_MAX);
  printf("FLT_MIN: %e\tFLT_MAX: %e\n", FLT_MIN, FLT_MAX);
  printf("DBL_MIN: %e\tDBL_MAX: %e\n", DBL_MIN, DBL_MAX);
  printf("LDBL_MIN: %Le\tLDBL_MAX: %Le\n", LDBL_MIN, LDBL_MAX);
  printf("FLT_DIG: %u\tDBL_DIG: %u\tLDBL_DIG: %u\n", FLT_DIG, DBL_DIG, LDBL_DIG);

  return;
}
