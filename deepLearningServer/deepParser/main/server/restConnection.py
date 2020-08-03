from socket import socket, AF_INET, SOCK_STREAM
from io import BytesIO
class RESTConnection:
    def __init__(self):
        self.PORT = 7575
        self.HOST = ''
        self.ADDR = (self.HOST, self.PORT)
        self.serverSocket = socket(AF_INET, SOCK_STREAM)
        self.serverSocket.bind(self.ADDR)

    def getConnection(self):
        self.serverSocket.listen()
        self.clientSocket, addr_info = self.serverSocket.accept()
        print('accept')
        print('--client information--')
        print(self.clientSocket, addr_info)
        return self


    def recv(self):
        length = self.clientSocket.recv(4)
        length = int.from_bytes(length, "little")
        print("len", length)
        return self.clientSocket.recv(length).decode()

    def send(self, text):
        length = len(text.encode())
        self.clientSocket.sendall(length.to_bytes(4, byteorder='little'))
        self.clientSocket.sendall(text.encode())