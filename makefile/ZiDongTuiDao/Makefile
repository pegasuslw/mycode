CFLAGS := -g
CC := gcc

SRC_FILES :=  $(wildcard *.c)
SRC_FILES := $(filter-out %.back.c,$(SRC_FILES))  # 不编译*.back.c文件
OBJS     :=  $(patsubst %.c,%.o,$(SRC_FILES)) 

CUR_DIR  :=  $(shell pwd)
TARGET   :=  $(notdir $(CUR_DIR))


.PHONY: all clean

all: $(TARGET)

$(TARGET): $(OBJS)
	$(CC) $(CFLAGS) $(OBJS) -o $@

$(foreach src_file,$(SRC_FILES),$(eval $(shell $(CC) -c -MM $(src_file)))) # -MM 自动生成.c依赖的.h文件

clean:
	@rm -f *.o
	@rm -f $(TARGET)
