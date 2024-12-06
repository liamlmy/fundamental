# 模型特征
### LR模型
$y(x)=w_0+\sum{w_ix_i}$
### FM模型
$y(x)=w_0+\sum{w_ix_i}+\sum\sum{w_{ij}x_ix_j}$\
$y(x)=w_0+\sum{w_ix_i}+\sum\sum{\langle v_i, v_j \rangle x_ix_j}$\
$y(x)=w_0+\sum{w_ix_i}+\frac{1}{2}(\sum\sum{\langle v_i, v_j \rangle x_ix_j}- \sum{\langle v_i, v_j \rangle x_i x_i})$\
$y(x)=w_0+\sum{w_ix_i}+\frac{1}{2}(\sum\sum\sum{\langle v_{i,t}, v_{j,t} \rangle x_ix_j} - \sum\sum{\langle v_{i,t}, v_{j,t} \rangle x_i x_i})$\
$y(x)=w_0+\sum{w_ix_i}+\frac{1}{2}\sum(\sum\sum{\langle v_{i,t}, v_{j,t} \rangle x_ix_j}- \sum{\langle v_{i,t}, v_{j,t} \rangle x_i x_i})$\
$y(x)=w_0+\sum{w_ix_i}+\frac{1}{2}\sum((\sum{v_{i,t}x_i})(\sum{v_{j,t}x_j}) - \sum{v_{i,t}^2 x_i^2})$\
$y(x)=w_0+\sum{w_ix_i}+\frac{1}{2}\sum((\sum{v_{i,t}x_i})^2 - \sum{v_{i,t}^2x_i^2})$\
该算法降低了模型的复杂度$O(n^2)$-->$O(kn)$，同时不依赖两个特征的共现关系
### FFM模型
$y(x)=w_0+\sum{w_ix_i}+\sum\sum{ \langle v_{i,f_j}, v_{j,f_i} \rangle x_i x_j}$\
复杂度$O(kn^2)$，工业界应用不多
### FNN
### PNN
### NFM
### AFM
### CAN
### Wide&Deep
### DeepFM
### XDeepFM
### DCN
### FiBiNet
### AutoInt

## auc详解
### 为什么auc对样本比例不敏感
### auc多高算高
#### 

## 如何提升模型的泛化能力
### dropout
### L1和L2正则项
$L1=min[\sum(w_T x_i - y_i)^2+\lambda ||w||_1]$，促使模型学到的权重尽可能稀疏，自动做特征筛选，排除不重要的特征\
$L2=min[\sum(w^T x_i - y_i)^2+\lambda ||w||_2]$，促使模型学到的权重尽可能小，降低模型复杂度，缓解特征共线性，避免过拟合
### early stop
### batchnorm vs layernorm
### resnet

# 序列模型

# 多目标模型

# 其他常见问题


