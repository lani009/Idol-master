from server.restConnection import RESTConnection
from server.tagParser import TagParser

tagParser = TagParser()
print(tagParser.parse_tag("싸가지 없음"))