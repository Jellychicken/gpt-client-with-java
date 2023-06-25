import sys
import openai


# 아래에 API키 집어넣기 ↓
openai.api_key = "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"

response = openai.Completion.create(
    model="text-davinci-003", 
    prompt=sys.argv[1], 
    temperature=0, 
    max_tokens=700
    )

print(response["choices"][0]["text"])
