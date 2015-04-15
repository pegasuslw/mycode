CFLAGS := -g

SRC_FILES :=  $(wildcard *.c)
SRC_FILES := $(filter-out %.back.c,$(SRC_FILES))  # 不编译*.back.c文件
OBJS     :=  $(patsubst %.c,%.o,$(SRC_FILES)) 

CUR_DIR  :=  $(shell pwd)
TARGET   :=  $(notdir $(CUR_DIR))


.PHONY: all clean

all: $(TARGET)

$(TARGET): $(OBJS)
	gcc $(CFLAGS) $(OBJS) -o $@

clean:
	@rm -f *.o
	@rm -f $(TARGET)
