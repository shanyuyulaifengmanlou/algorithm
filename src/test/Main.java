package test;

public class Main {
    // 判断一个输入的字符串是不是回文串
    // 需要去掉额外的空格 + 标点符号
    // 只有 英文和数字是合法的
    public boolean isReturnEssay(String str) {
        // 双指针
        char[] charArrs = str.toCharArray();
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            while (left < right && isIgnoreChar(charArrs[left])) {
                left++;
            }
            while (left < right && isIgnoreChar(charArrs[right])) {
                right--;
            }
            // 由于上文可能对 left 以及 right 进行了修改，因此需要再次确认是否满足 left < right
            if (left < right && Character.toLowerCase(charArrs[left]) != Character.toLowerCase(charArrs[right])) {
                return false;
            }
            // 正常的迭代操作，因为此时的两个字母已经完成判断了 或者说已经出现了 left >= right 的情况
            left++;
            right--;
        }
        return true;
    }

    // 如果不是英文  或者 不是数字，就不是合法的，应该忽略 (letter= 英文单词)
    public boolean isIgnoreChar(char c) {
        return !Character.isDigit(c) && !Character.isLetter(c);
    }

    public static void main(String[] args) {
        String test1 = "race a car";
        String test2 = "A man, a plan, a canal: Panama";
        Main test = new Main();
        System.out.println(test.isReturnEssay(test1));
        System.out.println(test.isReturnEssay(test2));
    }
}
