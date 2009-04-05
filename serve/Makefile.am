
bin_SCRIPTS	= waebric-serve

SUBDIRS = root

EXTRA_DIST	= $(wildcard *.sh.src) $(wildcard *.jar)

pkgdata_DATA	= $(wildcard *.jar)

CLEANFILES	= waebric-serve

WINSTONE 	= winstone-0.9.10.jar

% : %.sh.src classes
	sed "s@__PREFIX__@$(prefix)@g;\
		s@__BINDIR__@$(bindir)@g; \
		s@__WINSTONE__@$(WINSTONE)@g; \
		s@__DATA_DIR__@$(pkgdatadir)@g;\
		s@__VERSION__@$(VERSION)@g;" < $< > $@ && \
        chmod +x $@

classes: REPL.java
	javac -cp ${WINSTONE}:root/WEB-INF/lib/Tidy.jar -d root/WEB-INF/classes *.java

REPL.java: REPL.wae
	../scripts/waebricc $<

