import os
import requests
from openai import OpenAI

with open("prompt.txt") as f:
  genprompt = f.read()

print("---")
print(">" + genprompt)
print("---")

client = OpenAI(api_key=os.environ["OPENAI_API_KEY"])

response = client.images.generate(
  model="dall-e-3",
  prompt=genprompt,
  size="1024x1024",
  quality="standard",
  n=1,
)

image_url = response.data[0].url
print("-->" + image_url)

data = requests.get(image_url).content
f = open('besinnliches_image.png', 'wb')
f.write(data)
f.close()