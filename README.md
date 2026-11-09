# [Open the Student Grade Calculator](https://akhileshbittly-ux.github.io/StudentGradeCalculator/)

A simple grade calculator available as a browser app and a Java Swing desktop app.

## Web app

Open the [live calculator] (https://akhileshbittly-ux.github.io/StudentGradeCalculator/)  in a browser. Enter a student name, add subjects and marks out of 100, then select **Calculate result**. Use **Export report** to download a text report.

## Features

- Calculate the average percentage across subjects.
- Assign an overall grade: A+, A, B+, B, C, or D.
- Mark the result as **FAIL** if any subject mark is below 35.
- Add or remove subjects, reset the form, and export a report.

## Run the Java desktop app

Install JDK 17 or later. From the repository folder, compile and run:

```powershell
javac .\StudentGradeCalculator.java
java StudentGradeCalculator
```

The desktop app exports `StudentGradeResult.txt` to its current working directory.

## Deployment

The web app is hosted on GitHub Pages. The workflow in `.github/workflows/` publishes the repository when changes are pushed to the default branch.
