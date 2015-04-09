CFLAGS :=

SRC_FIES :=  $(wildcard *.c)
OBJS     :=  $(patsubst %.c,%.o,$(SRC_FIES)) 
CUR_DIR  :=  $(shell pwd)
TARGET   :=  $(notdir $(CUR_DIR))

.PHONY: all clean

all: $(TARGET)

$(TARGET): $(OBJS)
	gcc $(CFLAGS) $(OBJS) -o $@

clean:
	@rm -f *.o
	@rm -f $(TARGET)
