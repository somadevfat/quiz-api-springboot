import json

with open('src/main/resources/export.json', 'r', encoding='utf-8') as f:
    data = json.load(f)

unique_qids = set()
unique_quizzes = []
duplicates = []
for quiz in data:
    qid = quiz['qid']['en-US']
    if qid not in unique_qids:
        unique_qids.add(qid)
        unique_quizzes.append(quiz)
    else:
        duplicates.append(qid)

with open('src/main/resources/clean_questions.json', 'w', encoding='utf-8') as f:
    json.dump(unique_quizzes, f, indent=2, ensure_ascii=False)

print(f"Original items: {len(data)}")
print(f"Unique items: {len(unique_quizzes)}")
print(f"Removed {len(duplicates)} duplicates.")
if duplicates:
    print(f"Duplicate qids found: {list(set(duplicates))}") 