#include "bits/stdc++.h"

using namespace std;

struct node {
    int from;
    int to;
};
int cnt = 0;
vector<node> answer;

void hanoi(int from, int to, int other, int size) {
    if (size == 0) {
        return;
    }
    cnt++;
    hanoi(from, other, to, size - 1);
//    cout << from << " " << to << "\n";
    answer.push_back({from, to});
    hanoi(other, to, from, size - 1);
}

int main() {
    ios::sync_with_stdio(false), cin.tie(nullptr);

    int K;
    cin >> K;

    hanoi(1, 3, 2, K);

    cout << cnt << "\n";
    for (node n: answer) {
        cout << n.from << " " << n.to << "\n";
    }

    return 0;
}