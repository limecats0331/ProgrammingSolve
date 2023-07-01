#include "bits/stdc++.h"

using namespace std;
using ll = long long;
const ll INF = 1e18;


int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    int num;
    cin >> num;

    int answer = 0;
    while (num != 0) {
        int tmp = num % 10;
        num /= 10;
        if (tmp == 0) {
            num /= 10;
            answer += 10;
        } else {
            answer += tmp;
        }
    }

    cout << answer;

    return 0;
}

