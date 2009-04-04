
MAIN = waebric

SDFMODS := $(wildcard ../spec/languages/waebric/syntax/*.sdf)

all: waebric.def
	pdflatex ${MAIN}.tex


waebric.def: $(SDFMODS)
	cd ../spec; def-dump \
		-m languages/waebric/syntax/Waebric \
		-o ../doc/waebric.def ; \
	cd -