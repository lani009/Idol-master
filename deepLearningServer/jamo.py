from soynlp.hangle import decompose
import re
#자음과 모음으로 음절 나누는 코드.
doublespace_pattern = re.compile('\s+')

def jamo_sentence(sent):
    cjj = ()
    def transform(char):
        if char == ' ':
            return char
        cjj = decompose(char)
        if len(cjj) == 1:
            return cjj
        cjj_ = ''.join(c if c != ' ' else '-' for c in cjj)
        return cjj_

    sent_ = ''.join(transform(char) for char in sent)
    sent_ = doublespace_pattern.sub(' ', sent_)
    return sent_

jamo_sentence('어이고ㅋaaf 켁켁 아이고오aaaaa')
# 'ㅇㅓ-ㅇㅣ-ㄱㅗ- ㅋㅔㄱㅋㅔㄱ ㅇㅏ-ㅇㅣ-ㄱㅗ-ㅇㅗ-'