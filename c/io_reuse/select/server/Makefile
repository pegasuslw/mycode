CFLAGS := -g

SRC_FILES :=  $(wildcard *.c)
CUR_DIR  :=  $(shell pwd)
TARGET   :=  $(notdir $(CUR_DIR))

SRC_FILES := $(filter-out $(TARGET).back%,$(SRC_FILES))  # 不编译*.back.c文件
OBJS     :=  $(patsubst %.c,%.o,$(SRC_FILES)) 

.PHONY: all clean

all: $(TARGET)

$(TARGET): $(OBJS)
	gcc $(CFLAGS) $(OBJS) -o $@

clean:
	@rm -f *.o
	@rm -f $(TARGET)
