from socket import socket, AF_INET, SOCK_STREAM
from io import BytesIO
class RESTConnection:
    def __init__(self, port):
        self.PORT = port
        self.HOST = ''
        self.ADDR = (self.HOST, self.PORT)
        self.serverSocket = socket(AF_INET, SOCK_STREAM)
        self.serverSocket.bind(self.ADDR)

    def getConnection(self):
        self.serverSocket.listen()
        self.clientSocket, addr_info = self.serverSocket.accept()
        print('accept')
        print('--client information--')
        print(addr_info)
        return self


    def recv(self):
        length = self.clientSocket.recv(4)
        length = int.from_bytes(length, "little")
        recvStr = self.clientSocket.recv(length).decode()
        print('recv:', recvStr)
        return recvStr

    def send(self, text):
        length = len(str(text).encode())    # str 로 변환한뒤, 인코딩 하여 전송
        self.clientSocket.sendall(length.to_bytes(4, byteorder='little'))
        self.clientSocket.sendall(str(text).encode())
        print('send:', str(text).encode())