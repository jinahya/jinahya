#include <float.h>
#include <limits.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

#include "program2_11.h"

int program2_11(int argc, char** argv) {

  if (0) {
    return EXIT_SUCCESS;
  }
  printf("program2_11\n");
  printf("CHAR_MIN: %d\tCHAR_MAX: %d\tUCHAR_MAX: %d\n", CHAR_MIN, CHAR_MAX, UCHAR_MAX);
  printf("SHRT_MIN: %d\tSHRT_MAX: %d\tUSHAR_MAX: %d\n", SHRT_MIN, SHRT_MAX, USHRT_MAX);

  printf("Variables of type int store values from %d to %d\n", INT_MIN, INT_MAX);
  printf("Variables of type unsigned int store values from 0 to %u\n", UINT_MAX);
  printf("Variables of type long store values from %ld to %ld\n", LONG_MIN, LONG_MAX);
  printf("Variables of type unsigned long store values from 0 to %lu\n", ULONG_MAX);
  printf("Variables of type long long store values from %lld to %lld\n", LLONG_MIN, LLONG_MAX);
  printf("Variables of type unsigned long long store values from 0 to %llu\n", ULLONG_MAX);
  printf("\nThe size of the smallest positive non-zero value of type float is %.3e\n", FLT_MIN);
  printf("The size of the largest value of type float is %.3e\n", FLT_MAX);
  printf("The size of the smallest non-zero value of type double is %.3e\n", DBL_MIN);
  printf("The size of the largest value of type double is %.3e\n", DBL_MAX);
  printf("The size of the smallest non-zero value of type long double is %.3Le\n", LDBL_MIN);
  printf("The size of the largest value of type long double is %.3Le\n", LDBL_MAX);
  printf("\n Variables of type float provide %u decimal digits precision. \n", FLT_DIG);
  printf("Variables of type double provide %u decimal digits precision. \n", DBL_DIG);
  printf("Variables of type long double provide %u decimal digits precision. \n",
          LDBL_DIG);
  return EXIT_SUCCESS;
}
