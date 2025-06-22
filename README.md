# AI Optimization Simulated Annealing ❄

<img src="https://github.com/AnasAlSayed18/img/blob/7fb8ab510c22280e60194ee8d1f3e83db83cb4bc/AILogo1.png" width="250" />

## 📜 Overview
This project uses the **Simulated Annealing (SA)** algorithm to optimize the **15-dimensional Rastrigin function**, a benchmark optimization problem with many local minima.

The algorithm is implemented in **Java**, with a smooth **JavaFX** GUI to visualize the convergence process.

Despite the complexity of the function, our implementation reaches values **very close to the global minimum** in most runs. The solution space is explored using **Gaussian perturbations**, a **cooling schedule**, and **acceptance of worse solutions early on** to avoid getting stuck in local minima.

---

## ⚙️ Algorithm Details

- **Search Space**: 15-Dimensional, [-2.0, 2.0] per dimension
- **Objective**: Minimize  
  `f(x) = 10n + Σ [xᵢ² - 10cos(2πxᵢ)]`
- **Global Minimum**: `f(x) = 0` at `x = [0, ..., 0]`

### 🧪 SA Parameters
| Parameter             | Value         |
|----------------------|---------------|
| Initial Temperature  | 1000.0        |
| Cooling Rate (alpha) | 0.95          |
| Minimum Temperature  | 0.001         |
| Perturbation         | Gaussian (μ=0, σ=0.1) |
| Max Iterations       | 1000          |

---

## 🚀 Features
- **📉 Live Convergence Graph**: See how the function value improves over iterations
- **🧮 Output Log**: View best `f(x)` and solution vector
- **💾 Chart Export**: Save convergence graph as an image
- **🖱️ GUI Controls**: One-click optimization with JavaFX interface

---

## 📸 Screenshots

### Start Screen with Convergence Graph
![Start](https://github.com/AnasAlSayed18/img/blob/cd6af4b024dbb653e6302c884d4260acda8ead4b/ai1.png)


---

## 🧠 Sample Output

```text
Best f(x): 0.015
Best x: [0.01, -0.03, ..., 0.02]
```

---

## 📚 References

- [Simulated Annealing – Wikipedia](https://en.wikipedia.org/wiki/Simulated_annealing)
- [Rastrigin Function – Wikipedia](https://en.wikipedia.org/wiki/Test_functions_for_optimization)
- [GeeksforGeeks: SA in Java](https://www.geeksforgeeks.org/simulated-annealing-algorithm)
- COMP338 AI Course Material

---

## 👨‍💻 Authors
- **Anas Al Sayed** (1221020)
- **Abd Al-rheem Yaseen** (1220783)

Supervised by: *Dr. Radi Jarrar*

---

## 🎬 Demo Video
[Click here to view demo (Coming Soon)](#)
