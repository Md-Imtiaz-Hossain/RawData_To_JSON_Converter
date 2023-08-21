const gameWindowContainer = document.getElementById('canvas-container');
const imageToDraw = document.getElementById('siteImage');
const startButton = document.getElementById('startButton');
const restartButton = document.getElementById('restartGame');

const canvas = document.createElement('canvas');
const context = canvas.getContext('2d');
context.textAlign = 'center';
context.lineWidth = 1;

const words = [
  'Accessibility',
  'HTML5',
  'CSS3',
  'Javascript',
  'Responsive Design',
  'Semantic',
  'Modern Design',
  'Testing',
];

const gameWords = initArray(undefined);
let isFlippedImage = initArray(false);

//Funtion to initialize 2d arrays
function initArray(val) {
  const arr2D = [];
  for (let x = 0; x < 4; x++) {
    const arr = [];
    for (let y = 0; y < 4; y++) {
      arr.push(val);
    }
    arr2D.push(arr);
  }
  return arr2D;
}

maxWidth = gameWindowContainer.offsetWidth;
maxHeight = gameWindowContainer.offsetHeight;
canvas.width = maxWidth;
canvas.height = maxHeight;
const xOffset = parseInt(maxWidth / 4);
const yOffset = parseInt(maxHeight / 4);

let currentlySelected = {
  word: '',
  x: undefined,
  y: undefined,
};

disableClick = false;

startButton.addEventListener('click', startGame);
restartButton.addEventListener('click', restartGame);
canvas.addEventListener('click', function (event) {
  // CLick is disabled during two wrong words matched
  if (disableClick) return;
  const { x, y } = getMousePositionOnClick(canvas, event);
  const clickedRectX = Math.floor(x / xOffset);
  const clickedRectY = Math.floor(y / yOffset);
  if (isFlippedImage[clickedRectX][clickedRectY]) return;
  context.clearRect(
    clickedRectX * xOffset + 1,
    clickedRectY * yOffset + 1,
    xOffset - 1,
    yOffset - 1
  );
  context.font = 'bold 12px Nunito Sans';
  const textToWrite = gameWords[clickedRectX][clickedRectY];
  const splitText = textToWrite.split(' ');
  splitText.forEach((text, idx) => {
    const textWidth = context.measureText(text).width;
    context.fillText(
      text,
      clickedRectX * xOffset + xOffset / 2 - textWidth / 2,
      clickedRectY * yOffset + yOffset / 2 + 10 * (idx + 1)
    );
  });

  if (!currentlySelected.word) {
    currentlySelected.word = textToWrite;
    currentlySelected.x = clickedRectX;
    currentlySelected.y = clickedRectY;
    isFlippedImage[clickedRectX][clickedRectY] = true;
  } else {
    if (currentlySelected.word === textToWrite) {
      currentlySelected.word = '';
      isFlippedImage[clickedRectX][clickedRectY] = true;
      currentlySelected.x = undefined;
      currentlySelected.y = undefined;
    } else {
      disableClick = true;
      setTimeout(() => {
        clearCell(clickedRectX, clickedRectY);
        clearCell(currentlySelected.x, currentlySelected.y);
        drawImageOnCell(clickedRectX, clickedRectY);
        drawImageOnCell(currentlySelected.x, currentlySelected.y);
        disableClick = false;
      }, 1000);
      currentlySelected.word = '';
      isFlippedImage[clickedRectX][clickedRectY] = false;
      isFlippedImage[currentlySelected.x][currentlySelected.y] = false;
    }
  }
  if (checkIfWon()) {
    context.clearRect(0, 0, maxWidth, maxHeight);
    context.font = 'bold 40px Nunito Sans';
    const textToWrite = 'You won!';
    const textWidth = context.measureText(textToWrite).width;
    context.fillText(
      textToWrite,
      maxWidth / 2 - textWidth / 2,
      maxHeight / 2 - 10
    );
  }
});

function randomizeWords() {
  const allPositions = [];
  for (let x = 0; x < 4; x++) {
    for (let y = 0; y < 4; y++) {
      allPositions.push([x, y]);
    }
  }
  allPositions.sort((a, b) => 0.5 - Math.random());
  words.forEach((word, idx) => {
    const [x1, y1] = allPositions[idx * 2];
    const [x2, y2] = allPositions[idx * 2 + 1];
    gameWords[x1][y1] = word;
    gameWords[x2][y2] = word;
  });
}

function startGame() {
  randomizeWords();
  startButton.classList.add('dont-show');
  gameWindowContainer.appendChild(canvas);
  drawLines();
  drawAllImage();
  restartButton.classList.remove('dont-show');
}

function restartGame() {
  randomizeWords();
  context.clearRect(0, 0, maxWidth, maxHeight);
  drawLines();
  drawAllImage();
  isFlippedImage = initArray(false);
  currentlySelected = {
    word: '',
    x: undefined,
    y: undefined,
  };
}

function checkIfWon() {
  for (let i = 0; i < 4; i++) {
    if (
      !isFlippedImage[i].reduce(
        (accumulator, currectValue) => accumulator && currectValue
      )
    )
      return false;
  }
  return true;
}

// Drawing the border

function drawLines() {
  for (let i = 0; i < 3; i++) {
    context.moveTo(xOffset * (i + 1), 0);
    context.lineTo(xOffset * (i + 1), maxHeight);
    context.stroke();
  }
  for (let i = 0; i < 3; i++) {
    context.moveTo(0, yOffset * (i + 1));
    context.lineTo(maxWidth, yOffset * (i + 1));
    context.stroke();
  }
  context.strokeRect(0, 0, maxWidth, maxHeight);
}
function drawAllImage() {
  for (let i = 0; i < 4; i++) {
    for (let j = 0; j < 4; j++) {
      context.drawImage(
        imageToDraw,
        i * xOffset + 1,
        j * yOffset + 1,
        xOffset - 1,
        yOffset - 1
      );
    }
  }
}

function drawImageOnCell(x, y) {
  context.drawImage(
    imageToDraw,
    x * xOffset + 1,
    y * yOffset + 1,
    xOffset - 1,
    yOffset - 1
  );
}

function clearCell(x, y) {
  context.clearRect(x * xOffset + 1, y * yOffset + 1, xOffset - 1, yOffset - 1);
}

window.onload = drawAllImage;

// Get relative mouse position in canvas
function getMousePositionOnClick(canvas, event) {
  const rect = canvas.getBoundingClientRect();
  const clickPosition = {
    x: event.clientX - rect.left,
    y: event.clientY - rect.top,
  };
  return clickPosition;
}
