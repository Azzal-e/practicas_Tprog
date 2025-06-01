PROG:=main
SRCS:=main.cc paqueteria.cpp

CXX:=g++ -Wall -std=c++20

OBJS:=$(SRCS:.cc=.o)
OBJS:=$(OBJS:.cpp=.o)
DEPS:=$(OBJS:.o=.d)

all: $(PROG)

$(PROG): $(OBJS)
	$(CXX) -o $@ $^

.cc.o:
	$(CXX) -c -MMD $<

.cpp.o:
	$(CXX) -c -MMD $<

.PHONY: edit go

go: $(PROG)
	./$(PROG)

edit:
	@geany -s -i $(SRCS) *.h &

clean:
	@rm -f $(PROG) $(OBJS) $(DEPS) *.gch core tags

-include $(DEPS)