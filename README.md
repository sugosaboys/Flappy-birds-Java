# 🐦 Flappy Bird — Java Edition

A recreation of the classic **Flappy Bird** game built entirely in Java using `javax.swing` and `java.awt`.

---

## 📋 Table of Contents

- [About](#about)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Game](#running-the-game)
- [How to Play](#how-to-play)
- [Project Structure](#project-structure)
- [Built With](#built-with)
- [License](#license)

---

## 📖 About

This is a Java-based clone of the iconic mobile game **Flappy Bird**. The player controls a bird, attempting to fly between pairs of pipes without hitting them. Miss a gap or hit the ground — and it's game over!

This project was built as a personal Java practice project to strengthen skills in **game loops**, **2D rendering**, **collision detection**, and **event handling**.

---

## ✨ Features

- 🎮 Smooth gameplay with a consistent game loop
- 🏗️ Randomly generated pipe obstacles
- 📊 Real-time score tracking
- 💥 Collision detection (pipes & ground)
- 🔄 Restart functionality after game over

---

## 🚀 Getting Started

### Prerequisites

- **Java JDK 8** or higher
- **Visual Studio Code** with the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

Check your Java version:
```bash
java -version
```

---

### Installation

1. **Clone the repository:**
```bash
git clone https://github.com/sugosaboys/Flappy-birds-Java.git
```

2. **Open the project in VS Code:**
```bash
cd Flappy-birds-Java
code .
```

---

### Running the Game

1. Open the project folder in **VS Code**
2. Navigate to `FlappyBirdGame.java` (or your entry point file) in the Explorer panel
3. Click the **▶ Run** button at the top right of the editor
4. The game window will launch automatically

> Make sure the **Extension Pack for Java** is installed so VS Code can compile and run `.java` files.

---

## 🎮 How to Play

| Action | Control |
|--------|---------|
| Flap / Jump | `Space` or `Mouse Click` |
| Start Game | `Space` or `Mouse Click` |
| Restart (after Game Over) | `Space` or `Mouse Click` |

- **Fly through the gaps** between pipes to score a point.
- **Avoid hitting the pipes**, the ground, or the top of the screen.
- Your **score increases** by 1 for each pair of pipes you pass.
- Try to beat your **high score**!

---

## 📁 Project Structure

```
flappy-bird-java/
│
├── src/
│   ├── b-pipa-atas.png         
│   ├── b-pipa-bawah.png      
│   ├── bg.png          
│   ├── bird.png           
|   └── FlappyBirdGame.java
│
└── README.md
```

> *Update the structure above to match your actual file and folder names.*

---

## 🛠️ Built With

- **Java** — Core programming language
- **javax.swing** — Window and panel management
- **java.awt** — 2D graphics rendering
- **java.awt.event** — Keyboard and mouse input handling

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

## 🙏 Acknowledgements

- Original **Flappy Bird** game by Dong Nguyen

---

*Made with ☕ and Java*