let gameId = null;
let cells = [
    [0, 0, 0],
    [0, 0, 0],
    [0, 0, 0]
];
let gameOver = false;

function newGame() {
    fetch('/game', { method: 'POST' })
        .then(response => response.json())
        .then(data => {
            gameId = data.id;
            cells = data.board.cells;
            gameOver = false;
            renderBoard();
            updateStatus('Your turn');
        })
        .catch(error => {
            console.error('Failed to create game:', error);
            updateStatus('Failed to create game');
        });
}

function makeMove(row, col) {
    if (gameId === null) {
        updateStatus('Start a new game first');
        return;
    }

    if (gameOver) {
        return;
    }

    if (cells[row][col] !== 0) {
        return;
    }

    const previousCells = cells.map(r => [...r]);

    cells[row][col] = 1;
    renderBoard();
    updateStatus('Computer is thinking...');

    fetch(`/game/${gameId}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ board: { cells: cells } })
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => {
                    throw new Error(text);
                });
            }
            return response.json();
        })
        .then(data => {
            setTimeout(() => {
                cells = data.board.cells;
                renderBoard();
                updateStatusFromBoard();
            }, 600);
        })
        .catch(error => {
            console.error('Move failed:', error);
            cells = previousCells;
            renderBoard();
            updateStatus(error.message);
        });
}

function renderBoard() {
    const board = document.getElementById('board');
    board.innerHTML = '';

    for (let row = 0; row < 3; row++) {
        for (let col = 0; col < 3; col++) {
            const cell = document.createElement('div');
            cell.className = 'cell';
            cell.textContent = markToString(cells[row][col]);
            cell.onclick = () => makeMove(row, col);
            board.appendChild(cell);
        }
    }
}

function markToString(mark) {
    if (mark === 1) return 'X';
    if (mark === -1) return 'O';
    return '';
}

function updateStatus(text) {
    document.getElementById('status').textContent = text;
}

function updateStatusFromBoard() {
    const winner = checkWinner(cells);

    if (winner === 'X') {
        gameOver = true;
        updateStatus('You won! 🎉');
    } else if (winner === 'O') {
        gameOver = true;
        updateStatus('Computer won 😢');
    } else {
        updateStatus('Your turn');
    }
}

function checkWinner(cells) {
    const lines = [
        [[0, 0], [0, 1], [0, 2]],
        [[1, 0], [1, 1], [1, 2]],
        [[2, 0], [2, 1], [2, 2]],
        [[0, 0], [1, 0], [2, 0]],
        [[0, 1], [1, 1], [2, 1]],
        [[0, 2], [1, 2], [2, 2]],
        [[0, 0], [1, 1], [2, 2]],
        [[0, 2], [1, 1], [2, 0]]
    ];

    for (const line of lines) {
        const [a, b, c] = line;
        const value = cells[a[0]][a[1]];
        if (value !== 0
            && value === cells[b[0]][b[1]]
            && value === cells[c[0]][c[1]]) {
            return value === 1 ? 'X' : 'O';
        }
    }

    return null;
}

function isFull(cells) {
    return cells.flat().every(cell => cell !== 0);
}

document.getElementById('new-game').onclick = newGame;