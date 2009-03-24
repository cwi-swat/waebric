# $Id: Makefile.am 19971 2006-07-28 13:25:44Z jurgenv $

bin_PROGRAMS = Compile-Waebric Run-Waebric

pkgdata_DATA = Waebric.trm.tbl

EXTRA_DIST = \
	$(wildcard *.tbl) \
	$(wildcard *.eqs)

Compile_Waebric_CPPFLAGS = -DASF_MAIN $(ASC_SUPPORT_CFLAGS)
Compile_Waebric_SOURCES = Compile-Waebric.c
Compile_Waebric_LDADD = $(ASC_SUPPORT_LIBS)

Run_Waebric_CPPFLAGS = -DASF_MAIN $(ASC_SUPPORT_CFLAGS)
Run_Waebric_SOURCES = Run-Waebric.c
Run_Waebric_LDADD = $(ASC_SUPPORT_LIBS)

table:
	pt-dump -m languages/waebric/syntax/Waebric -o Waebric.trm.tbl

compile-spec: dump asfc

dump:
	eqs-dump -m languages/waebric/compile/Waebric -o Compile-Waebric.eqs
	eqs-dump -m languages/waebric/run/Waebric -o Run-Waebric.eqs

asfc:
	asfc -p Waebric.trm.tbl -i Compile-Waebric.eqs -o Compile-Waebric.c -v
	asfc -p Waebric.trm.tbl -i Run-Waebric.eqs -o Run-Waebric.c -v

