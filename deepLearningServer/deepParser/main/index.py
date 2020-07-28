from socket import *
from konlpy.tag import Kkma
from io import BytesIO
class RESTConnection:
    def __init__(self):
        self.PORT = 7575
        self.HOST = ''
        self.ADDR = (self.HOST, self.PORT)
        self.serverSocket = socket(AF_INET, SOCK_STREAM)
        self.serverSocket.bind(self.ADDR)
        self.serverSocket.listen(1)
        self.clientSocket, addr_info = self.serverSocket.accept()
        print('accept')
        print('--client information--')
        print(self.clientSocket)


    def recv(self):
        length = self.clientSocket.recv(4)
        length = int.from_bytes(length, "little");
        print("len", length)
        return self.clientSocket.recv(length).decode()

    def send(self, text):
        length = len(text.encode())
        self.clientSocket.sendall(length.to_bytes(4, byteorder='little'))
        self.clientSocket.sendall(text.encode())

class TagParser:
    def __init__(self):
        self.kkma = Kkma()

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

import json
from collections import OrderedDict

socket = RESTConnection()
tagParser = TagParser()
while True:
    recvStr = socket.recv()
    print(recvStr)
    print('parse_text' in recvStr)
    if 'parse_text' in recvStr:
        print("ininin")
        tag = tagParser.parse_tag(socket.recv())
        print(tag)
        json_data = OrderedDict()
        json_data["tag"] = tag
        socket.send(json.dumps(tag))
