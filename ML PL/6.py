# Step 1: Import Libraries
import numpy as np
import matplotlib.pyplot as plt

# Step 2: Create the Maze Environment
class MazeEnv:
    def __init__(self, grid, start, goal, traps=None):
        self.grid = grid  # 0 = free, 1 = wall
        self.start = start
        self.goal = goal
        self.traps = set(traps or [])
        self.rows, self.cols = grid.shape
        self.n_states = self.rows * self.cols
        self.n_actions = 4  # up, right, down, left
        self.reset()

    def to_state(self, pos):
        r, c = pos
        return r * self.cols + c

    def reset(self):
        self.pos = self.start
        return self.to_state(self.pos)

    def step(self, action):
        # Define actions: 0=up, 1=right, 2=down, 3=left
        dr = [-1, 0, 1, 0]
        dc = [0, 1, 0, -1]
        r, c = self.pos
        nr, nc = r + dr[action], c + dc[action]

        # Check bounds and walls
        if not (0 <= nr < self.rows and 0 <= nc < self.cols) or self.grid[nr, nc] == 1:
            reward = -5  # hit wall
            done = False
        else:
            self.pos = (nr, nc)
            if self.pos == self.goal:
                reward = 100
                done = True
            elif self.pos in self.traps:
                reward = -100
                done = True
            else:
                reward = -1  # normal move
                done = False
        return self.to_state(self.pos), reward, done, {}

# Step 3: Initialize Q-Table
env_grid = np.array([
    [0, 0, 0, 1, 0, 0],
    [0, 1, 0, 1, 0, 0],
    [0, 1, 0, 0, 0, 0],
    [0, 0, 1, 1, 0, 0],
    [0, 0, 0, 0, 0, 0]
])

env = MazeEnv(env_grid, start=(0, 0), goal=(0, 5), traps=[(4, 4)])
Q = np.zeros((env.n_states, env.n_actions))

# Step 4: Define Q-Learning Parameters
alpha = 0.1        # Learning rate
gamma = 0.95       # Discount factor
epsilon = 1.0      # Exploration rate
epsilon_min = 0.01
epsilon_decay = 0.995
episodes = 3000
max_steps = 200

# Step 5: Training the Agent
rewards_history = []

for ep in range(episodes):
    state = env.reset()
    total_reward = 0

    for step in range(max_steps):
        # ε-greedy policy
        if np.random.rand() < epsilon:
            action = np.random.randint(env.n_actions)
        else:
            action = np.argmax(Q[state])

        next_state, reward, done, _ = env.step(action)
        total_reward += reward

        # Q-learning update rule
        Q[state, action] += alpha * (reward + gamma * np.max(Q[next_state]) - Q[state, action])

        state = next_state

        if done:
            break

    epsilon = max(epsilon_min, epsilon * epsilon_decay)
    rewards_history.append(total_reward)

# Step 6: Visualize Learning Progress
plt.plot(rewards_history)
plt.title("Episode Reward over Time")
plt.xlabel("Episode")
plt.ylabel("Total Reward")
plt.show()

# Step 7: Evaluate the Learned Policy
state = env.reset()
done = False
steps = 0

print("\nAgent Path:\n")
while not done and steps < max_steps:
    action = np.argmax(Q[state])
    next_state, reward, done, _ = env.step(action)
    state = next_state
    steps += 1
    print(f"Step {steps}, Reward: {reward}")

