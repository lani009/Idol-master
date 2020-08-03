from server.restConnection import RESTConnection
from server.tagParser import TagParser
import json
from collections import OrderedDict
import threading

tagParser = 0
conn = 0

def index(socket):
    while True:
        instruction = socket.recv()
        print('instruction: ', instruction)
        if instruction == "parse_text":
            print('1')
            # 텍스트를 태그 형태로 파싱
            text = socket.recv()
            parsed_text = tagParser.parse_tag(text)
            json_data = OrderedDict()
            json_data['tag'] = parsed_text
            socket.send(json.dumps(json_data))
        elif instruction == "most_sim":
            print('2')
            # 입력받은 단어와 가장 유사한 단어들 10 개를 추출
            text = socket.recv()
            json_dict = json.loads(text)
            tags = json_dict["word"]
            json_data = OrderedDict()
            for i in tags:
                json_data[i] = tagParser.most_sim(i)
            conn.send(json.dumps(json_data))
        elif instruction == "place_user_sim":
            print('3')
            # 장소와 사람 사이의 유사도 계산
            text = socket.recv()
            json_dict = json.loads(text)
            place_tag = json_dict['place']
            user_tag = json_dict['user']
            sum = 0
            cnt = 0
            for i in place_tag:
                for j in user_tag:
                    sum += tagParser.wmd(i, j)
                    cnt += 1

            socket.send(sum / cnt)

        elif instruction == '0':
            break

    print('')

if __name__ == "__main__":
    print("python deep learning Server Starting!!!")
    tagParser = TagParser()
    tagParser.parse_tag("안녕하세요")
    conn = RESTConnection()
    print("parser ready")
    while True:
        thread = threading.Thread(target=index, args=(conn.getConnection(),))
        thread.start()