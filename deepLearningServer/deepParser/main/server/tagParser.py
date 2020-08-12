from konlpy.tag import Kkma
from gensim.models import fasttext

class TagParser:
    def __init__(self):
        self.kkma = Kkma()
        self.model = fasttext._load_fasttext_format("../fasttext.bin")

    def parse_tag(self, sentence):
        '''
        문장에서 태그를 파싱한다
        '''
        posed_sentence = self.kkma.pos(sentence) # 문장을 형태소 단위로 쪼갠다
        xr_list = []
        nng_va_list = [] # VA + NNG
        nng_va_concat = []  # VA와 NNG가 서로 짝을 찾아 합쳐진 리스트

        # XR(어근)만 찾아서 빼낸다
        for index,i in enumerate(posed_sentence):
            if i[1]=='XR':
                xr_list.append(i[0])

        # VA(형용사) 과 NNG(보통 명사) 만 빼내서 저장
        for index,i in enumerate(posed_sentence):
            if i[1]=='NNG' or i[1]=='VA':
                nng_va_list.append(i)

        # VA 인덱스의 -1 번째 항이 NNG 일경우 합친것을 다른 배열에 저장.
        for index,i in enumerate(nng_va_list):
            if nng_va_list[index][1] == 'VA':
                if index == 0:
                    continue
                elif nng_va_list[index-1][1]=='NNG':
                    nng_va_concat.append(nng_va_list[index-1][0] + " " + nng_va_list[index][0]+"음")

        # 만약 그렇지 않을 경우 패스// EX) 넓음
        # XR[] 과 VA[] 합치기
        return xr_list + nng_va_concat

    def pos(self, sentence):
        '''
        문장을 형태소 별로 토큰화
        '''
        return self.kkma.pos(sentence)

    def most_sim(self, word):
        '''
        가장 비슷한 단어 추출
        '''
        return self.model.wv.most_similar(word)

    def wmd(self, tag1, tag2):
        '''
        tag1와 tag2 사이의 거리를 리턴
        '''
        return self.model.wv.wmdistance(tag1, tag2)

    def sim(self, word1, word2):
        return self.model.wv.similarity(word1, word2)