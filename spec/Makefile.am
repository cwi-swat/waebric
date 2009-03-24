# $Id: Makefile.am 19971 2006-07-28 13:25:44Z jurgenv $

bin_PROGRAMS = Compile-Waebric Import-Waebric Check-Waebric Run-Waebric

pkgdata_DATA = Waebric.trm.tbl

EXTRA_DIST = \
	$(wildcard *.tbl) \
	$(wildcard *.eqs)

Compile_Waebric_CPPFLAGS = -DASF_MAIN $(ASC_SUPPORT_CFLAGS)
Compile_Waebric_SOURCES = Compile-Waebric.c
Compile_Waebric_LDADD = $(ASC_SUPPORT_LIBS)

Check_Waebric_CPPFLAGS = -DASF_MAIN $(ASC_SUPPORT_CFLAGS)
Check_Waebric_SOURCES = Check-Waebric.c
Check_Waebric_LDADD = $(ASC_SUPPORT_LIBS)

Import_Waebric_CPPFLAGS = -DASF_MAIN $(ASC_SUPPORT_CFLAGS)
Import_Waebric_SOURCES = Check-Waebric.c
Import_Waebric_LDADD = $(ASC_SUPPORT_LIBS)

Run_Waebric_CPPFLAGS = -DASF_MAIN $(ASC_SUPPORT_CFLAGS)
Run_Waebric_SOURCES = Run-Waebric.c
Run_Waebric_LDADD = $(ASC_SUPPORT_LIBS)

compile-specs: table dump-check dump-import dump-run dump-compile  asfc-check asfc-run asfc-compile asfc-import

table:
	pt-dump -m languages/waebric/syntax/Waebric -o Waebric.trm.tbl

dump-import:
	eqs-dump -m languages/waebric/import/Waebric -o Import-Waebric.eqs

dump-check:
	eqs-dump -m languages/waebric/check/Waebric -o Check-Waebric.eqs

dump-run:
	eqs-dump -m languages/waebric/run/Waebric -o Run-Waebric.eqs

dump-compile:
	eqs-dump -m languages/waebric/compile/Waebric -o Compile-Waebric.eqs

asfc-check: 
	asfc -p Waebric.trm.tbl -i Check-Waebric.eqs -o Check-Waebric.c -v

asfc-import: 
	asfc -p Waebric.trm.tbl -i Import-Waebric.eqs -o Import-Waebric.c -v

asfc-run: 
	asfc -p Waebric.trm.tbl -i Run-Waebric.eqs -o Run-Waebric.c -v

asfc-compile: 
	asfc -p Waebric.trm.tbl -i Compile-Waebric.eqs -o Compile-Waebric.c -v


