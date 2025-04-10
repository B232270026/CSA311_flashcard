# CSA311_flashcard system
Буруу хариултуудыг эхэнд гаргаж асуух
java -cp target/classes com.flashcard.FlashcardApp flashcards.txt --order worst-first

Хамгийн сүүлд алдсан алдаануудыг эхэнд гаргаж асуух
java -cp target/classes com.flashcard.FlashcardApp flashcards.txt --order receny-mistakes

Санамсаргүй байдлаар картыг эрэмбэлэх
java -cp target/classes com.flashcard.FlashcardApp flashcards.txt --order random

Зөв хариултыг хэдэн удаа зөв хариулахыг тохируулах
java -cp target/classes com.flashcard.FlashcardApp flashcards.txt --repetitiond <num>

Асуулт хариултуудын байрыг нь солих
java -cp target/classes com.flashcard.FlashcardApp flashcards.txt --invertCards 
