

bin_SCRIPTS	= preproc-waebric waebric waebricc waebricb waebric-ast

EXTRA_DIST	= $(wildcard *.sh.src)

CLEANFILES	= preproc-waebric waebric waebricc waebricb waebric-ast

% : %.sh.src 
	sed "s@__PREFIX__@$(prefix)@g;\
		s@__BINDIR__@$(bindir)@g; \
		s@__DATA_DIR__@$(pkgdatadir)@g;\
		s@__VERSION__@$(VERSION)@g;" < $< > $@ && \
        chmod +x $@

