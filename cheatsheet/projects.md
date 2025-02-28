# 交叉特征模型
## 常见模型
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

***

# 序列模型
## 策略简介
### 为什么选用DIN模型？
用户不同历史兴趣对当前候选店铺的影响程度不同
### 为什么用RNN模型
用户的外卖兴趣会随时间发生周期性转移
### 为什么没有用更复杂的DIEN/BST/SIM等模型
* 外卖业务的数据不充分
* 线上inference的性能不支持，RT过高
### 序列特征
* 时间维度
  * 长期/短期
* 行为维度
  * 正向反馈：点击、搜索、下单
  * 负向反馈：差评
* side info
  * shop_id
  * cate/tag/month sold/...基础属性&静态画像
  * 时段、场景信息
  * 距离当前请求的时间间隔+log变换
### mini-batch自适应正则化
* 只更新当前批次中非0输入的权重，同时权重按照输入中非0的数量做归一化处理
* 降低过拟合风险
### dice激活函数
* 按照输入进行分布偏移，同时保证拐点可导
### gauc计算指标
* 用户维度auc
* 当用户行为不多时，gauc会发生抖动，还是以auc为准
## 衡量指标
### auc与cvr不一致
* 工程问题：特征一致性 / 样本穿越
* 指标问题：gauc / uauc，auc只能说明模型可以拉大正负样本的区别，但对实际的排序或者用户的浏览体验不一定有效果
* 数据发生偏移，模型的泛化能力较弱
* 划分数据：用户群体、时段、场景等维度
## follow up
### DIEN
* GRU + attention
* GRU推导公式
  * $h_t=(1-z_t)*h_{t-1}+z_t*\tilde{h_t}$
  * $z_t=\sigma{(W_z\cdot{[h_{t-1}, x_t]})}$
  * $\tilde{h_t}=\tanh(W\cdot[r_t*h_{t-1},x_t])$
  * $r_t=\sigma{(W_r\cdot{[h_{t-1},x_t]})}$
### DISN
* 双向LSTM + attention
* LSTM推导公式
  * $y_t=W_{yh}h_t+b_y$
  * $h_t=o_t\odot{m_t}$
  * $o_t=\sigma{(W_{xo}x_t+W_{ho}h_{t-1}+b_o)}$
  * $m_t=\tanh{(c_t)}$
  * $c_t=c_{t-1}\odot{f_t}+g_t\odot{i_t}$
  * $f_t=\sigma{(W_{xf}x_t+W_{hf}h_{t-1}+b_f)}$
  * $g_t=\tanh{(W_{xg}x_t+W_{hg}h_{t-1}+b_g)}$
  * $i_t=\sigma{(W_{xi}x_i+W_{hi}h_{t-1}+b_i)}$
### BST
* Transformer Encoder，target itemt和history item拼接到一起训练
### SIM
* 向量hard search / soft search取topk个做序列再过attention或transformer
### ETA
### MIND
* 能够获取用户多个不同的兴趣，从而丰富召回
* Multi-Interest Extractor Layer：通过多层胶囊网络提取
* B2I动态路由：$b_{ij}$高斯初始化；多个胶囊网络共享S双线映射矩阵；不同用户的兴趣胶囊数量不同
## 常见问题
### 为什么不用fancy模型
* 算法选型要结合业务，并不是fancy模型就一定合适
* 外卖业务不同于信息流推荐，数据体量不至于使用SIM等超长序列模型
* 工程几件成本问题
### 训练方式
* 90天全量训练：兴旺模型忘掉一些历史数据
  * 平台希望通过低价吸引用户
  * 对于长期不访问的用户再来的时候按照新用户发券
* 模型大小
  * feature_field
  * feature_slot
  * 参数量
  * dump出的h5文件大小
* 激活函数
  * sigmoid
    * $\frac{1}{1+e^{-x}}$，S型，0.5中心，范围[0, 1]
    * $f'(x)=f(x)(1-f(x))$
    * 计算exp时耗时较多，不是关于原点对称，会出现梯度弥散
  * tanh
    * $\frac{1-e^{-2x}}{1+e^{-2x}}$，S型，0中心，范围[-1, 1]
    * $f'(x)=1-\tanh^2{x}$
    * 比sigmoid计算速度快，但也有梯度弥散问题
  * relu
    * $\max{(x, 0)}$
    * 收敛速度快，解决了部分梯度弥散的问题
    * 在负数部分神经元死亡，leaky relu可以解决该问题
  * leaky relu
    * $\begin{cases}x, \quad x>=0 \\ \alpha x,\quad x<0,a\approx0.01 \end{cases}$
    * 具有relu的所有优点，且不存在神经元死亡的问题
  * softmax
    * 
  * maxout
    * $$
    * 对relu和leaky relu的一般归纳
    * 计算量相对复杂
  * swish
    * $x*sigmoid(\alpha x)$
    * 非单调，处处可导
  * gelu
  * dice
* 优化器
  * SGD
  * BGD
  * MBGD
  * Momentum
  * Adagard
  * RMSprop
  * Adam
  * ftrl
* loss函数
  * 
***

# 多目标模型

***

### AUC详解
##### ROC曲线
* 横轴：false positive rate
* 纵轴：true positive rate
##### AUC计算公式
* $AUC=(totalPosRank - (num_p + 1) * num_p / 2) / (num_p * num_n)$
#### 为什么auc对样本比例不敏感？
* 随机取一对正负样本，正样本得分大于负样本的概率。假设正样本得分是s+，负采样均匀，那么负样本s- < s+ 和负样本s- > s+的概率应该是一样的
* ROC横轴FPR只关注负样本，与正样本无关；纵轴TPR只关注正样本，与负样本无关。所以横纵轴都不受正负样本比例的影响，auc也不会受其影响
#### auc多高算高？
* max auc：基于现有数据的理论最高auc，有些样本特征完全一样，但是label不一样，这类贝叶斯错误率算法是不发解决的，所以这种情况下max auc不可能达到1
#### auc缺陷有哪些？https://cloud.tencent.com/developer/article/1759372
1. 忽略了预测的概率值和模型的拟合度，只对区分正负样本敏感，而对实际的概率值不敏感
2. 只区分正负样本，而对正样本或负样本内的排序不敏感
3. 无法反应准确率、召回率等信息
4. 没有给出模型误差的空间部分信息，比如对哪一类样本的预测误差较多等
5. auc的misleading 

***

### 准确率、精确率、召回率、F1
* 准确率 = (TP + TN) / (TP + TN + FP + FN)
* 精确率 = TP / (TP + FP)
* 召回率 = TP / (TP + FN)
* F1 = 2TP / (2TP + FP + FN)

***

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

# 交叉熵公式推到及物理意义
## 交叉熵是衡量实际概率分布P和理想概率分布Q之间的差异度，即相对熵-信息熵
## 公式

# 神经网络权重初始化不能为0，而线性回归和LR可以？
## 神经网络不可以
### 没有学习能力：前向传播和反向传播过程中，每一层接收到相同的输入和梯度更新，更新后每一层权重依然相同，相当于模型没有学习能力
### 激活函数无作用：激活函数一般是非线性，权重为0则输入为0，激活函数输出是固定值，无法通过梯度下降来学习
### 带来梯度爆炸或梯度消失
### 学习效率低
## LR可以
### 公式推到可以：w1和w2都是0，但是dw1和dw2不会因为x1和x2不同而不同，且不为0，权重能够得到更新

# 点击率预估的后验校准
## 公式
## 公式
## 公式

# 推荐系统的消偏
## 机器学习的本质是历史数据的分布拟合，其有效性假设是training和serving的数据是独立同分布(IID)的，但实际中会存在bias
## bias类型
### 位置bias
### 曝光bias
### 选择bias：用户倾向于给自己喜欢或不喜欢的item打分
### 一致性bias：用户打分倾向于和群体观点保持一致
### 热门bias
## 位置/曝光bias的解决办法
### data augmentation
#### all negative with confidence: 未被观测到的都作为负样本池，再进行采样和赋予权重
#### IPS
#### doubly robust
### 建模
#### imputation model：对那些未曝光的样本打上标签，然后基于这些imputed label来训练模型，这个imputation model可以单独训练，也可做joint training (曝光概率+点击概率)
#### multi-task model：ESMM、ESAM、ESCM
#### 位置信息
##### 训练位置特征，预测值均为0
##### 将位置和设备、app version等影像浏览的信息embedding到一个shallow网络后concat

# 如何解决长尾分布
## 预训练：热门item_id学习充分，可以对item_id的embedding做正则化
## 样本：调整样本权重；负采样；扩充样本；辅助正样本
## 特征：引入更多泛化特征
## 模型结构：
### 物品的参数自适应学习，类似PEPNet
### google CDN将物品特征拆分为记忆特征和泛化特征两个单独专家网络建模，通过gate（输入为物品样本数）来控制item_id embedding表征组成
### 两个目标流行度和兴趣分别建模
## loss函数：focal loss

# 一个策略不work怎么解决
## 工程校验
## 模型校验
### 指标问题？auc、gauc等
### 数据分布不一致
#### 数据分布漂移（随时间、随位置）
#### 冰山效应
### 过拟合 vs 欠拟合
### 是否在某些特定维度（用户群体、内容群体、场景）上表现不如预期
### 反馈机制
#### 反馈数据是否有问题
### 策略反思

# 推荐结果的问题？
## 解决：产品盈利模式所需的用户群体和当前产品使用的用户群体之间的错位
### 保持住现有的用户群体，尝试引入适合这个群体的盈利模式（广告商）
### 保持住现有的盈利模式，尝试引入一些对广告消费群体感兴趣的内容
## 模型不公平
### 保住大多数人的利益，牺牲少部分人的利益
### 保住行为多的人利益，牺牲行为少的人利益

# 如何发现潜在增长点
## 现有平台数据的deep dive
### 当前未解决的问题
### 长尾内容
### 个性化
### 用户行为反馈
## 市场分析
### follow竞对策略
### 新用户群体拓展
### 内容生产的视角推荐
## 多样性推荐
### 局部最优 -> 全局最优

# 如何做项目规划
## 定义问题
### 要解决哪些问题？bad case？
### 预期的收益是什么
## 技术方案
### 抽象为数学问题、机器可解决的问题
### 明确解决路径：逻辑地推的过程，最好能看到策略的演进过程
### 可调整型：提权、降权、过滤、可解释性
## 评估问题
### 明确目标
### 离线评估+在线评估

# 集成学习 bagging vs boosting
## bagging（民主）：每个基础模型都有一票，最终民主投票决定：分类最多的为结果，回归均值；bagging结果方差一般较小
## boosting（精英）：每一轮提升那些错误率小的基础模型的权重，降低错误率高的基础模型权重；boosting结果偏差一般较小

# CDBT / XGB / LightGBM / CatBoost

# pointwise vs pairwise vs listwise？为什么精排用pointwise
## pointwise
### 交叉熵
### 可能不满足IID，同时loss被召回数量多的query支配
### doc的相对顺序没有考虑清楚
## pairwise
### L=max(0,m - h(q, c+) + h(q, c-))
### 样本是n的二次方，一个doc标注错影响会被放大
### 可能不满足IID
### 公式
## listwise
### Y和S的KL散度，NDCG
### LambdaMart 、AdaRank、SoftRank
### 公式

# 推荐如何引入搜索的信息

# 特征发生维度坍塌怎么处理

# 低频小样本长周期算法设计
## 风控/房产/汽车/旅行
## 辅助任务
## 补充

# element-wise乘法在浅层和深层的区别（SeNet vs PPNet）
## 各有利弊
### 浅层删选特征，降低噪声干扰
### 深层更接近结果，影响更大，非线性学习能力更好

# 如何判断模型是否过拟合并且避免
## 判断
### 训练集和验证集的误差
### 绘制学习曲线：将训练误差和验证误差随训练轮数的变化绘制，若训练误差下降而验证误差开始增加或波动，则可能出现过拟合
### 交叉验证
### 观察参数和权重，参数过大或波动过大可能过拟合
## 避免
### 数据增强
### 正则化
### early stop
### dropout
### 降低模型结构复杂度
### 集成方法

# 在线学习

# 如何筛选特征
## 特征重要性分析
### lr & xgb
### 数据分析：特征共线性、特征的重要性
### 通过mask做特征对比的消融实验
### 阿里：网络瘦身
#### 预训练
#### multi-hash
#### droprank

# 什么是好的特征工程
* 高质量特征
  * 有区分性 informative
  * 特征之间相互独立 independent
  * 简单易于理解 simple
* 伸缩性 scalable：支持大数据量、高基数特征
* 高效率 efficient：支持高并发预测、低纬度
* 灵活性 flexible：对下游任务有一定的普适性
* 自适应性 adaptive：对数据分布的变化有一定的鲁棒性

# 常用的特征变换
## 数值型
## 类别型
## 时序特征
