

bin_SCRIPTS	= waebric waebricc waebricb

EXTRA_DIST	= $(wildcard *.sh.src)

CLEANFILES	= waebric waebricc waebricb

% : %.sh.src 
	sed "s@__PREFIX__@$(prefix)@g;\
		s@__BINDIR__@$(bindir)@g; \
		s@__DATA_DIR__@$(pkgdatadir)@g;\
		s@__VERSION__@$(VERSION)@g;" < $< > $@ && \
        chmod +x $@

